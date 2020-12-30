package com.ngn.spring.project.cdb.admin.arcitect;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.services.ArchitectServices;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.PersonalInfoDTO;
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
@RequestMapping(value = "/admin_architect")
public class AtchitectAdminController extends BaseController {
    @Autowired
    private ArchitectServices services;

    @Autowired
    private CommonService commonService;
   /* @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("taskList", services.getTaskList());
        return "admin/engineer_tasklist";
    }
    @RequestMapping(value = "/tasklist/",method = RequestMethod.GET)
    public String tasklist(Model model) {
        model.addAttribute("taskList", services.getTaskList());
        return "admin/engineer_tasklist";
    }*/
    @RequestMapping(value = "/architect_tasklist", method = RequestMethod.GET)
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
       // model.addAttribute("myTaskList", contractorActionService.gMyTaskList(loggedInUser.getUserID()));
        return "admin/architect_tasklist";
    }

    @RequestMapping(value = "/emptylayout/openApplication", method = RequestMethod.GET)
    public String send2MyOrGroupTask(HttpServletRequest request,String appNo,String type,Model model) {
        if(type.equalsIgnoreCase("release")){
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
                return "admin/architect_tasklist";

            }else{
                return null;
            }
        }else {
            services.assignMyTask(appNo, getLoggedInUser().getUserID(),type);
            model.addAttribute("modeOfPayment", services.getModePayment());
            ArchitectDto dto = services.getArchitetDetails(appNo);
            model.addAttribute("appDetails", dto);
            if (dto.getServiceTypeId().equalsIgnoreCase("New Registration")) {
                if (request.isUserInRole("ROLE_PAYMENT")) {
                    return "architect/registration/architect_payment";
                } else {
                    return "architect/registration/architect_verification";
                }
            } else if (dto.getServiceTypeId().equalsIgnoreCase("Renewal of CDB Certificate")) {
                if (request.isUserInRole("ROLE_PAYMENT")) {
                    return "architect/renewal/renewal_payment_architect";
                } else {
                    return "architect/renewal/architect_renewal_verification";
                }
            } else if (dto.getServiceTypeId().equalsIgnoreCase("Cancellation of Registration")) {
                return "architect/cancellation/cancellation_verification";
            } else {
                return null;
            }
        }
    }

    @RequestMapping(value = "/emptylayout/updatereject", method = RequestMethod.GET)
    public String updatereject(HttpServletRequest request,ArchitectDto dto,Model model){
        dto=services.updateReject(dto, getLoggedInUser().getUserID(),request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have rejected this application due to <b>"+dto.getRemarks()+"</b>. Please check status from view status with application number: <b>"+dto.getReferenceNo()+"</b></div>");
        }else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to reject this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/verifyArchitectRegistration", method = RequestMethod.GET)
    public String verifyArchitectRegistration(HttpServletRequest request,ArchitectDto dto,Model model){
        dto=services.updateVerification(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have verified application with application number: <b>"+dto.getReferenceNo()+"</b>. The application has been forwarded for further approval. Thank you</div>");
        }else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to verify this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/approveArchitectRegistration", method = RequestMethod.GET)
    public String approveArchitectRegistration(HttpServletRequest request,ArchitectDto dto,Model model){
        dto.setServiceTypeId(request.getParameter("servicefor"));
        dto=services.approveArchitectRegistration(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(request.getParameter("servicefor").equalsIgnoreCase("cancel")){
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved application for the cancellation of certificate with application number: <b>"+dto.getReferenceNo()+"</b>. The details are updated and certificate is cancelled. Thank you</div>");
            } else{
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved application with application number: <b>"+dto.getReferenceNo()+"</b>. The application has been forwarded for further payment approval. Thank you</div>");
            }
        } else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to approve this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/approveAndGenerateCertificate", method = RequestMethod.POST)
    public String approveAndGenerateCertificate(HttpServletRequest request,ArchitectDto dto,Model model,PersonalInfoDTO commonDto){
        dto.setServiceTypeId(request.getParameter("servicefor"));
        dto=services.approveAndGenerateCertificate(dto, getLoggedInUser().getUserID(), request,commonDto);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved payment for application : <b>"+dto.getReferenceNo()+"</b>.And CDB Number is:<b>"+dto.getCdbNo()+"</b> You may print certificate and issue. Thank you</div>");
        } else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to approve this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/architect_print_Certificate", method = RequestMethod.GET)
    public String architect_print_Certificate(ModelMap model, HttpServletRequest request) {
        model.addAttribute("printList", services.getPrintList());
        return "architect/architectPrint";
    }

    @RequestMapping(value = "/emptylayout/printarchitectInfo", method = RequestMethod.GET)
    public String printarchitectInfo(HttpServletRequest request,ArchitectDto dto,Model model,String cdbNo){
        model.addAttribute("registrationDetails", commonService.populateApplicantDetails(cdbNo));
        model.addAttribute("App_Details", commonService.populateApplicantDetails(cdbNo));
        return "architect/architect_printInformation";
      //  return "jasperreport";
    }

    @RequestMapping(value ={"/emptylayout/openRejectedApplication"}, method = RequestMethod.GET)
    public String fetchRejectedAppDetails(HttpServletRequest request,String appNo,Model model) {
        ArchitectDto dto = services.fetchRejectedAppDetails(appNo);
        model.addAttribute("appDetails", dto);
        return "architect/rejectedApplications/rejectedIndex";
    }
}

