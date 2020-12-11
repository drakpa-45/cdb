package com.ngn.spring.project.cdb.admin.contractor;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ==================================================================================
 * Created by user on 3/10/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin")
public class ContractorTaskListController extends BaseController {

    @Autowired
    private ContractorNRActionService contractorNRActionService;

    @RequestMapping(value = "/contractor", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request,String service) {
        loggedInUser = gLoggedInUser(request);
        String appStatus = null;
        if(service == null || service.isEmpty()){
            service = (String) request.getSession().getAttribute("SERVICE");
        }else{
            request.getSession().setAttribute("SERVICE",service);
        }
        if(request.isUserInRole("ROLE_APPROVER")) {
            appStatus = ApplicationStatus.VERIFIED.getCode();
        }else if(request.isUserInRole("ROLE_VERIFIER")){
            appStatus = ApplicationStatus.UNDER_PROCESS.getCode();
        }else if(request.isUserInRole("ROLE_PAYMENT")){
            appStatus = ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
        }
        model.addAttribute("groupTaskList", contractorNRActionService.gTaskList(null,appStatus,service));
        model.addAttribute("myTaskList", contractorNRActionService.gTaskList(loggedInUser.getUserID(),appStatus,service));

        return "admin/contractor/contractor_tasklist";
    }
    @RequestMapping(value = "/contractor/send2MyOrGroupTask", method = RequestMethod.POST)
    public String send2MyOrGroupTask(HttpServletRequest request,String appNo,String flag) {
        contractorNRActionService.send2MyOrGroupTask(appNo,flag,getLoggedInUser().getUserID());
        return "redirect:/admin/contractor";
    }

    @RequestMapping(value = "/contractor/{appNo}/{flag}", method = RequestMethod.GET)
    public String redirectToAction(HttpServletRequest request,RedirectAttributes attributes,@PathVariable String appNo,@PathVariable String flag) {
        String service = (String)request.getSession().getAttribute("SERVICE");
        //String appStatus = contractorNRActionService.getApplicationStatus(appNo);
        contractorNRActionService.send2MyOrGroupTask(appNo,flag,getLoggedInUser().getUserID());
        attributes.addFlashAttribute("appNo", appNo);
        switch (service){
            case "NR":
                return "redirect:/admin/contractorNRAction";
            case "RC":
                return "redirect:/admin/contractorRCAction";
            case "CC":
                return "redirect:/admin/contractorCCAction";
            case "OS":
                return "redirect:/admin/contractorOSAction";
        }
        return "redirect:/admin/contractorNRAction";
    }
}
