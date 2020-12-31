package com.ngn.spring.project.cdb.architect.controller;

import bt.gov.ditt.sso.client.SSOClientConstants;
import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.entity.ArchitectDocument;
import com.ngn.spring.project.cdb.architect.services.ArchitectServices;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.engineer.service.EngineerServices;
import com.ngn.spring.project.cdb.survey.service.SurveyServices;
import com.ngn.spring.project.cdb.trade.service.SpecializedService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.ResponseMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.Date;

/**
 * Created by USER on 3/19/2020.
 */
@Controller
@RequestMapping("")
public class ArchitectController extends BaseController {
    @Autowired
    private ArchitectServices services;
    @Autowired
    private SpecializedService spservices;
    @Autowired
    private SurveyServices suservices;
    @Autowired
    private EngineerServices engineerServices;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/architectIndex")
    public String redirectToPage( ModelMap model) {

        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";

        model.addAttribute("fee_details", services.getFeesDetals("Architect"));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        return "/architect/registration/architectIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/architects/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cid,String type) {
        try{
            ResponseMessage personal=commonService.getPersonalInfo(cid, type);
            return personal;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/architects/isMailUnique", method = RequestMethod.GET)
    public ResponseMessage isMailUnique(HttpServletRequest request) {
        try{
            ResponseMessage isMailUnique=services.isMailUnique(request);
            return isMailUnique;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/architects/isCIDUnique", method = RequestMethod.GET)
    public ResponseMessage isCIDUnique(HttpServletRequest request, String cidNo) {
        try{
            ResponseMessage isCIDUnique=services.isCIDUnique(cidNo);
            return isCIDUnique;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }

    @RequestMapping(value = "/public_access/emptylayout/saveArchitect",method = RequestMethod.POST)
    public String saveArchitect(ArchitectDto dto,@RequestParam("files") MultipartFile[] files, HttpServletRequest request,ModelMap model) {
        UserSessionDetailDTO user = (UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);

        try {
                ResponseMessage personal = services.saveArchitect(dto, user);
                ArchitectDto resdto = (ArchitectDto) personal.getDto();
                String cid = "";
                if (user == null) {
                    cid = resdto.getCidNo();
                } else {
                    cid = user.getCid();
                }
                if (personal.getStatus() == 1) {
                    services.saveDoc(files, resdto.getCrpArchitectId(), "RegistrationOfArchitect", null);
                    personal.setResponseText("Your application for <label class='control-label'>Registration of Architect</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as SMS notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");

                    String mailContent = "<b>Application No: "+resdto.getReferenceNo()+" is submitted sucessfully on "+new Date()+" with Construction Development Board (CDB)." +
                            "This is to acknowledge for the registration of the Architect with Construction Development Board (CDB)." +
                            " Your application will processed in due course. You can check the status of the application using CID no or Application number provided." +
                            " You will also be notified via email when your application is approved." +
                            "Thank You," +
                            "(CDB)";
                    try {
                        MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Registered Success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Registration of Architect</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as SMS notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
                return "/architect/acknowledgement";
        } catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            return  null;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/renewal"}, method = RequestMethod.GET)
    public String renewal(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("param");
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("categoryList", spservices.category());
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));

        if(type.split("999")[0].equalsIgnoreCase("Architect")){
            model.addAttribute("cdbNo",type.split("999")[1]);
            responseMessage = services.check4Renewal(type.split("999")[1],true);
            model.addAttribute("renewalCheck",responseMessage);
            model.addAttribute("fee_details", services.getFeesDetals("Architect"));
          //  model.addAttribute("checkOngoingApplication", services.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(type.split("999")[1]));
            return "architect/renewal/architect_renewal_index";
        }else if(type.split("999")[0].equalsIgnoreCase("Survey")) {
            model.addAttribute("cdbNo",type.split("999")[1]);
            responseMessage = suservices.check4Renewal(type.split("999")[1],true);
            model.addAttribute("renewalCheck",responseMessage);
            model.addAttribute("fee_details", suservices.getFeesDetals("Survey"));
       //     model.addAttribute("checkOngoingApplication", suservices.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateSurveyApplicantDetails(type.split("999")[1]));
            return "survey/renewal/survey_renewal_index";
        }else if(type.split("999")[0].equalsIgnoreCase("Engineer")) {
            model.addAttribute("cdbNo",type.split("999")[1]);
            responseMessage = engineerServices.check4Renewal(type.split("999")[1],true);
            model.addAttribute("renewalCheck",responseMessage);
            model.addAttribute("fee_details", engineerServices.getFeesDetals("Engineer"));
           // model.addAttribute("checkOngoingApplication", engineerServices.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateEngineerApplicantDetails(type.split("999")[1]));
            return "engineer/renewal/engineer_renewal_index";
        } else {
            model.addAttribute("cdbNo",type.split("999")[1]);
            responseMessage = spservices.check4Renewal(type.split("999")[1],true);
            model.addAttribute("renewalCheck",responseMessage);
            model.addAttribute("fee_details", spservices.getFeesDetals("Specialized"));
           // model.addAttribute("checkOngoingApplication", spservices.checkOngoingApplication(type.split("999")[1]));
            String checkForOwnership=spservices.checkOwnerShipType(type.split("999")[1]);

                model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(type.split("999")[1]));
                return "trade/sptrade_renewal_index";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/cancel"}, method = RequestMethod.GET)
    public String cancel(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("param");

        if(type.split("999")[0].equalsIgnoreCase("Architect")){
           // model.addAttribute("cdbNo",type.split("999")[1]);
            model.addAttribute("checkOngoingApplication", services.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(type.split("999")[1]));
            return "architect/cancellation/architect_cancellation_index";
        }else if(type.split("999")[0].equalsIgnoreCase("Specialized")){
            //model.addAttribute("cdbNo",type.split("999")[1]);
            model.addAttribute("checkOngoingApplication", services.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(type.split("999")[1]));
            return "trade/spTrade_cancellation_index";
        }else if(type.split("999")[0].equalsIgnoreCase("Survey")){
            // model.addAttribute("cdbNo",type.split("999")[1]);
            model.addAttribute("checkOngoingApplication", suservices.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateSurveyApplicantDetails(type.split("999")[1]));
            return "survey/cancellation/survey_cancellation_index";
        }else if(type.split("999")[0].equalsIgnoreCase("Engineer")){
            model.addAttribute("checkOngoingApplication", engineerServices.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateEngineerApplicantDetails(type.split("999")[1]));
            return "engineer/cancellation/engineer_cancellation_index";
        }
        return null;
    }

    @RequestMapping(value ="/public_access/emptylayout/submitRenwalApplication", method = RequestMethod.POST)
    public String submitRenwalApplication(ArchitectDto dto,@RequestParam("files") MultipartFile[] files,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveArchitectRenenwal(dto, loginDTO.getUserId());
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            if(personal.getStatus()==1){
                services.saveDoc(files,resdto.getCrpArchitectId(),"RenewalOfArchitect",loginDTO.getUserId());
                personal.setResponseText("Your application for <label class='control-label'>Renewal of Architect</label> has been submitted successfully and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            }
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal of Architect</label> has been submitted successfully and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }

    @PreAuthorize("isAuthenticated()")
      @RequestMapping(value ={"/public_access/emptylayout/submitcancellation"}, method = RequestMethod.POST)
      public String submitcancellation(ArchitectDto dto,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveArchitectcancellation(dto, loginDTO.getUserId());
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Cancellation of Architect</label> has been submitted successfully and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }

    @RequestMapping(value ={"/public_access/emptylayout/openRejectedApplication"}, method = RequestMethod.GET)
    public String fetchRejectedAppDetails(HttpServletRequest request,String appNo,Model model) {
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("categoryList", spservices.category());
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));

        model.addAttribute("fee_details", services.getFeesDetals("Architect"));
        ArchitectDto dto = services.fetchRejectedAppDetails(appNo);
            model.addAttribute("registrationDetails", dto);
        return "architect/rejection/rejectedIndex";
              //return "/architect/rejection/rejectedIndex";
        }

    private Boolean isEmailUnique(String email) {
        return commonService.isEmailUnique(email);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/public_access/emptylayout/donwloadFiles",method = RequestMethod.GET)
    public String donwloadFiles(@RequestParam("type") String type,ArchitectDto dto,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid"),requesttype=request.getParameter("type");

        try{
            ArchitectDocument doc = services.getDocumentDetailsByDocId(uploadDocId);
            byte[] fileContent = downloadFile(doc.getDocumentPath());
            if(requesttype.equalsIgnoreCase("view")){
                if(doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("JPG")||doc.getDocumentName().substring(doc.getDocumentName().length()-4).equalsIgnoreCase("jpeg") || doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("png")){
                    response.setContentType("image/jpeg");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("pdf")){
                    response.setContentType("APPLICATION/PDF");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-4).equalsIgnoreCase("docx")){
                    response.reset();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "inline;filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("xls")){
                    response.setContentType("APPLICATION/vnd.ms-excel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-4).equalsIgnoreCase("xlsx")){
                    response.setContentType("Application/x-msexcel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else{
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-disposition", "attachment; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            }
            else{
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="+doc.getDocumentName());
                response.getOutputStream().write(fileContent);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        }catch (Exception e){
            System.out.print(e);
            return  ""+e;
        }
        return null;
    }
    public static byte[] downloadFile(String uploadUlr) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(uploadUlr);
        return IOUtils.toByteArray(fileInputStream);
    }
}
