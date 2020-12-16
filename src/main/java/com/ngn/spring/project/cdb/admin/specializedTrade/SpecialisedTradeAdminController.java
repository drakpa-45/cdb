package com.ngn.spring.project.cdb.admin.specializedTrade;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.PersonalInfoDTO;
import com.ngn.spring.project.cdb.trade.dto.TradeDto;
import com.ngn.spring.project.cdb.trade.service.SpecializedService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by USER on 3/29/2020.
 */

@Controller
@RequestMapping(value = "/admin_specializedTrade")
public class SpecialisedTradeAdminController extends BaseController {
    @Autowired
    private SpecializedService services;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/specializedTrade_tasklist", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
        loggedInUser = gLoggedInUser(request);
        String type=request.getParameter("param");
        if(type.equalsIgnoreCase("new")){
            type="55a922e1-cbbf-11e4-83fb-080027dcfac6";
        }
        if(type.equalsIgnoreCase("renew")){
            type="45bc628b-cbbe-11e4-83fb-080027dcfac6";
        }
        if(type.equalsIgnoreCase("cancellation")){
            type="acf4b324-cbbe-11e4-83fb-080027dcfac6";
        }
        if(request.isUserInRole("ROLE_APPROVER")) {
            model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.VERIFIED.getCode(), "Group", getLoggedInUser().getUserID(),type));
            model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.VERIFIED.getCode(),"mytask",getLoggedInUser().getUserID(),type));
        }else if(request.isUserInRole("ROLE_VERIFIER")){
            model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.UNDER_PROCESS.getCode(),"Group",getLoggedInUser().getUserID(),type));
            model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.UNDER_PROCESS.getCode(),"mytask",getLoggedInUser().getUserID(),type));

        }else if(request.isUserInRole("ROLE_PAYMENT")){
           model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode(), "Group", getLoggedInUser().getUserID(),type));
            model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode(),"mytask",getLoggedInUser().getUserID(),type));
        }
        return "admin/specialisedTrade_tasklist";
    }

    @RequestMapping(value = "/emptylayout/openApplication", method = RequestMethod.GET)
    public String send2MyOrGroupTask(HttpServletRequest request,String type,Model model) {
        String appNo = request.getParameter("appNo");

        if (type.equalsIgnoreCase("release")) {
            String cmnServiceTypeId = request.getParameter("cmnServiceType");
            String assignMyTask = services.assignMyTask(appNo, getLoggedInUser().getUserID(), type);
            if(assignMyTask.equalsIgnoreCase("Success")){
                if(cmnServiceTypeId == null || cmnServiceTypeId.isEmpty()){
                    cmnServiceTypeId = (String) request.getSession().getAttribute("SERVICE");
                }
                else if(cmnServiceTypeId.equalsIgnoreCase("new")){
                    cmnServiceTypeId="55a922e1-cbbf-11e4-83fb-080027dcfac6";
                }
                else if(cmnServiceTypeId.equalsIgnoreCase("renew")){
                    cmnServiceTypeId="45bc628b-cbbe-11e4-83fb-080027dcfac6";
                }
                else if(cmnServiceTypeId.equalsIgnoreCase("cancellation")){
                    cmnServiceTypeId="acf4b324-cbbe-11e4-83fb-080027dcfac6";
                }

                if(request.isUserInRole("ROLE_APPROVER")) {
                    model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.VERIFIED.getCode(), "Group", getLoggedInUser().getUserID(),cmnServiceTypeId));
                    model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.VERIFIED.getCode(),"mytask",getLoggedInUser().getUserID(),cmnServiceTypeId));
                }else if(request.isUserInRole("ROLE_VERIFIER")){
                    model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.UNDER_PROCESS.getCode(),"Group",getLoggedInUser().getUserID(),cmnServiceTypeId));
                    model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.UNDER_PROCESS.getCode(),"mytask",getLoggedInUser().getUserID(),cmnServiceTypeId));

                }else if(request.isUserInRole("ROLE_PAYMENT")){
                    model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode(), "Group", getLoggedInUser().getUserID(),cmnServiceTypeId));
                    model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode(),"mytask",getLoggedInUser().getUserID(),cmnServiceTypeId));
                }
                return "admin/specialisedTrade_tasklist";

            }else{
                return null;
            }
        } else {
            services.assignMyTask(appNo, getLoggedInUser().getUserID(), type);
            model.addAttribute("modeOfPayment", services.getModePayment());
            //model.addAttribute("categoryList", services.category());
            String ownershipType = services.getOwnershipType(appNo);
            if (ownershipType.equalsIgnoreCase("Individual")) {
                TradeDto dto = services.getTradeDetails(appNo);
                model.addAttribute("appDetails", dto);
                if (dto.getServiceTypeId().equalsIgnoreCase("New Registration")) {
                    if (request.isUserInRole("ROLE_PAYMENT")) {
                        return "trade/trade_payment";
                    } else {
                        return "trade/trade_verification";
                    }
                } else if (dto.getServiceTypeId().equalsIgnoreCase("Renewal of CDB Certificate")) {
                    if (request.isUserInRole("ROLE_PAYMENT")) {
                        return "trade/trade_renewal_vPayment";
                    } else {
                        return "trade/sptrade_renewal_verification";
                    }
                } else if (dto.getServiceTypeId().equalsIgnoreCase("Cancellation of Registration")) {
                    return "trade/sp_cancellation_verification";
                } else {
                    return null;
                }
            }
        }return null;
    }

    @RequestMapping(value = "/emptylayout/updatereject", method = RequestMethod.GET)
    public String updatereject(HttpServletRequest request,TradeDto dto,Model model){
        dto=services.updateReject(dto, getLoggedInUser().getUserID(),request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have rejected this application due to <b>"+dto.getRemarks()+"</b>. Please check status from view status with application number: <b>"+dto.getReferenceNo()+"</b></div>");
        } else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to reject this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/verifySpTradeRegistration", method = RequestMethod.GET)
    public String verifySpTradeRegistration(HttpServletRequest request,TradeDto dto,Model model){
        dto=services.updateVerification(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have verified application with application number: <b>"+dto.getReferenceNo()+"</b>. The application has been forwarded for further approval. Thank you</div>");
        }else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to verify this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/approveSpTradeRegistration", method = RequestMethod.GET)
    public String approveSpTradeRegistration(HttpServletRequest request,TradeDto dto,Model model){
        dto.setServiceTypeId(request.getParameter("servicefor"));
            dto=services.updateApproval(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(request.getParameter("servicefor").equalsIgnoreCase("cancel")){
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved application for the cancellation of certificate with application number: <b>"+dto.getReferenceNo()+"</b>. The details are updated and certificate is cancelled. Thank you</div>");
            } else {
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved application with application number: <b>"+dto.getReferenceNo()+"</b>. Thank you</div>");
            }
        } else {
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to approve this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/approveAndGenerateCertificate", method = RequestMethod.POST)
    public String approveAndGenerateCertificate(HttpServletRequest request,TradeDto dto,Model model,PersonalInfoDTO commonDto){
        dto.setServiceTypeId(request.getParameter("servicefor"));
        if(request.getParameter("ownershipType").equalsIgnoreCase("Incorporation") || request.getParameter("ownershipType").equalsIgnoreCase("Sole Proprietorship")){
          //  dto=services.approveAndGenerateCertificateIncorporation(dto, getLoggedInUser().getUserID(), request,commonDto);
        }else {
           // dto=services.approveAndGenerateCertificate(dto, getLoggedInUser().getUserID(), request);
                /*this is used for approver before */
            dto = services.approveSpTradeRegistrationSole(dto, getLoggedInUser().getUserID(), request,commonDto);
        }
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved payment for application : <b>"+dto.getReferenceNo()+"</b>.and the CDB number is: <b>"+dto.getCdbNo()+"</b> You may print certificate and issue. Thank you</div>");
        } else {
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to approve this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/specializedTrade_print_Certificate", method = RequestMethod.GET)
    public String specializedTrade_print_Certificate(ModelMap model, HttpServletRequest request) {
        model.addAttribute("printList", services.getPrintList());
        return "trade/specializedTradePrint";
    }

    @RequestMapping(value = "/emptylayout/printarchitectInfo", method = RequestMethod.GET)
    public String printarchitectInfo(HttpServletRequest request,ArchitectDto dto,Model model, String cdbNo){
        model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(cdbNo));
        model.addAttribute("App_Details", commonService.populateSpApplicantDetails(cdbNo));
        return "trade/spTrade_printInformation";
       // return "jasperreport";
    }
}

