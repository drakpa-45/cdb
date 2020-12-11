package com.ngn.spring.project.cdb.engineer.controller;

import bt.gov.ditt.sso.client.SSOClientConstants;
import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.engineer.model.EngineerAttachment;
import com.ngn.spring.project.cdb.engineer.service.EngineerServices;
import com.ngn.spring.project.cdb.survey.entity.SurveyDocument;
import com.ngn.spring.project.cdb.survey.service.SurveyServices;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.ResponseMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
public class EngineerController extends BaseController {
    @Autowired
    private EngineerServices services;

    @Autowired
    private CommonService commonService;
    //@RequestMapping(value = "/public/architectIndex")
    @RequestMapping(value = "/engineerNR")
    public String redirectToPage( ModelMap model) {
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";

        model.addAttribute("fee_details", services.getFeesDetals("Engineer"));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        return "/engineer/registration/engineerIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/engineer/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cid) {
        try{
            ResponseMessage personal=commonService.getPersonalInfo(cid);
            return personal;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/engineer/isMailUnique", method = RequestMethod.GET)
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
    @RequestMapping(value = "/engineer/isCIDUnique", method = RequestMethod.GET)
    public ResponseMessage isCIDUnique(HttpServletRequest request, String cidNo) {
        try{
            ResponseMessage isCIDUnique=services.isCIDUnique(cidNo);
            return isCIDUnique;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }

    @RequestMapping(value = "/public_access/emptylayout/saveEngineer",method = RequestMethod.POST)
    public String saveSurvey(ArchitectDto dto,@RequestParam("files") MultipartFile[] files, HttpServletRequest request,ModelMap model) {
        UserSessionDetailDTO user = (UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);
        loggedInUser = gLoggedInUser(request);
        try {
                ResponseMessage personal = services.saveEngineer(dto, user);
                ArchitectDto resdto = (ArchitectDto) personal.getDto();
                String cid = "";
                if (user == null) {
                    cid = resdto.getCidNo();
                } else {
                    cid = user.getCid();
                }
                if (personal.getStatus() == 1) {
                    services.saveDoc(files, resdto.getCrpEngineerId(), "RegistrationOfEngineer", "CITIZEN");
                    personal.setResponseText("Your application for <label class='control-label'>Registration of Engineer</label> has been submitted and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");

                    String mailContent = "<b>Application No: "+resdto.getReferenceNo()+" is submitted successfully on "+new Date()+" with Construction Development Board (CDB)." +
                            "This is to acknowledge for the registration of the Engineer with Construction Development Board (CDB)." +
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
                model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Registration of Engineer</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + "."+"</b> <br><p>You will receive an email as well as SMS notification once taken further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
                return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
        }
        return  null;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/emptylayout/submitRenwalApplicationEngineer"}, method = RequestMethod.POST)
    public String submitRenwalApplication(ArchitectDto dto,@RequestParam("files") MultipartFile[] files,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveEngineerRenenwal(dto, loginDTO.getUserId());
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            if(personal.getStatus()==1){
                services.saveDoc(files,resdto.getCrpEngineerId(),"RenewalOfEngineer",loginDTO.getUserId());
                personal.setResponseText("Your application for <label class='control-label'>Renewal of Engineer</label> has been submitted successfully and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as SMS notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            }
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal of Engineer</label> has been submitted successfully and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as SMS notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }
    @PreAuthorize("isAuthenticated()")
      @RequestMapping(value ={"/public_access/emptylayout/submitengineercancellation"}, method = RequestMethod.POST)
      public String submitcancellation(ArchitectDto dto,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveEngineercancellation(dto, loginDTO.getUserId());
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Cancellation of Engineer </label> has been submitted successfully and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }

    @RequestMapping(value = "/public_access/emptylayout/resubmissionEngineer")
    public String resubmissionsurvey( ModelMap model, HttpServletRequest request) {
        //String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
       // model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        String appNo=request.getParameter("appNo");
        ArchitectDto dto=services.getDetails(appNo);
        model.addAttribute("appDetails", dto);
        return "/survey/surveyResubmissionIndex";
    }

    private Boolean isEmailUnique(String email) {
        return commonService.isEmailUnique(email);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/public_access/emptylayout/donwloadFilesEngineer",method = RequestMethod.GET)
    public String donwloadFiles(@RequestParam("type") String type,ArchitectDto dto,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid"),requesttype=request.getParameter("type");
        try{
            EngineerAttachment doc = services.getDocumentDetailsByDocId(uploadDocId);
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
