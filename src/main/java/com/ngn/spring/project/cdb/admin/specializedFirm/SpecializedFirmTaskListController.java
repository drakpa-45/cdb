package com.ngn.spring.project.cdb.admin.specializedFirm;

import com.ngn.spring.project.base.BaseController;
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
public class SpecializedFirmTaskListController extends BaseController {

    @Autowired
    private SpecializedFirmActionService SpecializedFirmActionService;

    @RequestMapping(value = "/specializedFirm", method = RequestMethod.GET)
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
        model.addAttribute("groupTaskList", SpecializedFirmActionService.gTaskList(null,appStatus,service));
        model.addAttribute("myTaskList", SpecializedFirmActionService.gTaskList(loggedInUser.getUserID(),appStatus,service));

        return "admin/specializedFirm/specializedFirm_tasklist";
    }
    @RequestMapping(value = "/specializedFirm/send2MyOrGroupTask", method = RequestMethod.POST)
    public String send2MyOrGroupTask(HttpServletRequest request,String appNo,String flag) {
        SpecializedFirmActionService.send2MyOrGroupTask(appNo,flag,getLoggedInUser().getUserID());
        return "redirect:/admin/specializedFirm";
    }

    @RequestMapping(value = "/specializedFirm/{appNo}", method = RequestMethod.GET)
    public String redirectToAction(RedirectAttributes attributes,@PathVariable String appNo, HttpServletRequest request) {
        String service = (String)request.getSession().getAttribute("SERVICE");
        //String appStatus = contractorNRActionService.getApplicationStatus(appNo);
        attributes.addFlashAttribute("appNo", appNo);
        switch (service){
            case "NR":
                return "redirect:/admin/specializedFirm/action";
            case "RC":
                return "redirect:/admin/specializedFirmRCAction";
            case "CC":
                return "redirect:/admin/specializedFirmCCAction";
            case "OS":
                return "redirect:/admin/specializedFirmOSAction";
        }
        return "redirect:/admin/specializedFirm/action";
    }
}
