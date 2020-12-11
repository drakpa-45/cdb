package com.ngn.spring.project.cdb.trade.controller;

import bt.gov.ditt.sso.client.SSOClientConstants;
import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.trade.dto.TradeDto;
import com.ngn.spring.project.cdb.trade.dto.TradeFeesDto;
import com.ngn.spring.project.cdb.trade.entity.TradeDocument;
import com.ngn.spring.project.cdb.trade.service.SpecializedService;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.DropdownDTO;
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
import java.util.List;

/**
 * Created by USER on 5/5/2020.
 */
@Controller
@RequestMapping("")
public class SpecializedController extends BaseController {

    @Autowired
    private SpecializedService services;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/tradeIndex")
    public String redirectToPage( ModelMap model) {
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String cmnDesignation = "599fbfdc-a250-11e4-b4d2-080027dcfac6";
        String cmnServiceType = "08dada52-c651-11e4-b574-080027dcfee6";

        model.addAttribute("fee_details", services.getFeesDetals("Specialized"));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        //model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        model.addAttribute("categoryList", services.category());
        model.addAttribute("designationList", commonService.gCmnListItem(cmnDesignation));
     //   model.addAttribute("equipmentList", services.gEquipment());
        model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
        return "/trade/tradeIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/trade/getCommonDropdown", method = RequestMethod.GET)
    public List<DropdownDTO> getDropdownDetails(@RequestParam("sl_no") String sl_no, @RequestParam("type") String type) {
        return commonService.getDropdownDetails(sl_no, type);
    }

    @ResponseBody
    @RequestMapping(value = "/trade/getPersonalInfo", method = RequestMethod.GET)
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
    @RequestMapping(value = "/trade/isMailUnique", method = RequestMethod.GET)
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
    @RequestMapping(value = "/trade/isCIDUnique", method = RequestMethod.GET)
    public ResponseMessage isCIDUnique(HttpServletRequest request, String cidNo) {
        try{
            ResponseMessage isCIDUnique=services.isCIDUnique(cidNo);
            return isCIDUnique;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }

    @RequestMapping(value = "/public_access/emptylayout/saveSpecializedTrade",method = RequestMethod.POST)
    public String saveSpecializedTrade(TradeDto dto,TradeFeesDto tradeFeesDto,@RequestParam("files") MultipartFile[] files,@RequestParam("fileEQ") MultipartFile[] fileEQ,@RequestParam("fileHR") MultipartFile[] fileHR, HttpServletRequest request,ModelMap model) {
        UserSessionDetailDTO user = (UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);
        ResponseMessage personal=null;
        loggedInUser = gLoggedInUser(request);
        try {
            personal = services.saveSpecializedTrade(dto, loggedInUser);
                if (personal.getStatus() == 1) {
                    TradeDto resdto = (TradeDto) personal.getDto();
                    String cid = "";
                    if (user == null) {
                        cid = resdto.getCidNo();
                    } else {
                        cid = user.getCid();
                    }
                    if (personal.getStatus() == 1) {
                        String saveWrk = services.saveWrkClassification(dto, tradeFeesDto, null, request);
                        if (saveWrk.equalsIgnoreCase("success")) {
                            services.saveDoc(files, resdto.getCrpSpecializedTradeId(), "RegistrationOfSpecializedTrade", null);
                            personal.setResponseText("Your application for <label class='control-label'>Registration of Specialized Trade</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as SMS notification once taken further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
                        } else {
                            model.addAttribute("status", "failed");
                            return null;
                        }
                    }
                    model.addAttribute("cidDetails", services.fetchdtls(resdto));
                    model.addAttribute("acknowledgement_message", "<center>Your application for <label class='control-label'>Registration of Specialized Trade</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + ".</center> <center>"+"You will receive an email as well as SMS notification once taken further action.</center> <center><label class='control-label'>You can track your application using above Application Number.</label></center>");
//                    ("acknowledgement_message", "<center>Your application for <label class='control-label'>Registration of Specialized Trade</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + ".</center> <center>"+"You will receive an email as well as SMS notification once taken further action.</center> <center><label class='control-label'>You can track your application using above Application Number.</label></center>");

                    String mailContent = "<b>Application No: "+resdto.getReferenceNo()+" is submitted successfully on "+new Date()+" with Construction Development Board (CDB)." +
                            "This is to acknowledge for the registration of the Specialized Trade with Construction Development Board (CDB)." +
                            " Your application will processed in due course. You can check the status of the application using CID no or Application number provided." +
                            " You will also be notified via email when your application is approved." +
                            "Thank You," +
                            "(CDB)";
                    try {
                        MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Registered Success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "/trade/tradePrintPage";
                } else{
                    model.addAttribute("acknowledgement_message", "<div class ='text-danger'>Your application for <label class='control-label'>Registration of Specialized Trade/Firm</label> has failed. Please try again by providing valid information for all required fields.</div>");
                    return "/architect/acknowledgement";
                }
            //}
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            return  null;
        }
        //return String.valueOf(model.addAttribute("status","failed"));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/emptylayout/submitRenwalApplicationSpTrade"}, method = RequestMethod.POST)
    public String submitRenwalApplicationSpTrade(TradeDto dto,TradeFeesDto tradeFeesDto,@RequestParam("files") MultipartFile[] files,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveSpTradeRenenwal(dto, loginDTO.getUserId());
            TradeDto resdto=(TradeDto)personal.getDto();
            if(personal.getStatus()==1) {
                String saveWrk = services.saveWrkClassificationrenewal(dto, tradeFeesDto, null, request);
                if (saveWrk.equalsIgnoreCase("success")) {
                    services.saveDoc(files, resdto.getCrpSpecializedTradeId(), "RenewalOfSpecialTrade", loginDTO.getUserId());
                    //personal.setResponseText("Your application for <label class='control-label'>Renewal Of SpecialTrade</label> has been submitted and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
                    model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal of SpecialTrade</label> has been submitted successfully and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as SMS notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
                    return "/architect/acknowledgement";
                } else {
                    model.addAttribute("status", "failed");
                    model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal of SpecialTrade</label> has been not submitted. Please try again</label>");
                    return "/architect/acknowledgement";
                }
            } else {
                model.addAttribute("status", "failed");
                model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal of SpecialTrade</label> has been not submitted. Please try again</label>");
                return "/architect/acknowledgement";
            }
              //  model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal Of SpecialTrade</label> has been submitted and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
               // return "/architect/acknowledgement";

        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/cancelspTrade"}, method = RequestMethod.GET)
    public String cancel(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("param");
        if(type.split("999")[0].equalsIgnoreCase("SpecializedTrade")){
            model.addAttribute("checkOngoingApplication", services.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(type.split("999")[1]));
            return "trade/spTrade_cancellation_index";
        }
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/emptylayout/submitspcancellation"}, method = RequestMethod.POST)
    public String submitcancellation(TradeDto dto,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserSessionDetailDTO user = (UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);
        ResponseMessage personal=null;
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            String checkForOwnership=services.checkOwnerShipType(request.getParameter("cdbNo"));
            if(checkForOwnership.equalsIgnoreCase("Incorporated")){
                personal=services.updateApplicationCancellation(dto, user, loggedInUser, request);
            }
            else {
                 personal = services.saveSpTradecancellation(dto, loginDTO.getUserId());
            }
            TradeDto resdto=(TradeDto)personal.getDto();
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Cancellation Of Specialized Trade</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/public_access_trade/emptylayout/donwloadFiles",method = RequestMethod.GET)
    public String donwloadFiles(@RequestParam("type") String type,TradeDto dto,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid"),requesttype=request.getParameter("type");
        TradeDocument doc =null;
        try{
             if(request.getParameter("docType").equalsIgnoreCase("doc")){
                 doc = services.getDocumentDetailsByDocId(uploadDocId);
            }
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
    private Boolean isEmailUnique(String email) {
        return commonService.isEmailUnique(email);
    }

}