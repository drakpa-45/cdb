package com.ngn.spring.project.cdb.admin.consultant;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.admin.consultant.registration.ConsultantNRActionController;
import com.ngn.spring.project.cdb.admin.consultant.registration.ConsultantNRActionService;
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
public class ConsultantTaskListController extends BaseController {

    @Autowired
    private ConsultantNRActionService consultantActionService;

    @RequestMapping(value = "/consultant", method = RequestMethod.GET)
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
        model.addAttribute("groupTaskList", consultantActionService.gTaskList(null,appStatus,service));
        model.addAttribute("myTaskList", consultantActionService.gTaskList(loggedInUser.getUserID(),appStatus,service));

        return "admin/consultant/consultant_tasklist";
    }

    @RequestMapping(value = "/consultant/send2MyOrGroupTask", method = RequestMethod.POST)
    public String send2MyOrGroupTask(HttpServletRequest request,String appNo,String flag) {
        consultantActionService.send2MyOrGroupTask(appNo,flag,getLoggedInUser().getUserID());
        return "redirect:/admin/consultant";
    }

    @RequestMapping(value = "/consultant/{appNo}/{flag}", method = RequestMethod.GET)
    public String redirectToAction(HttpServletRequest request,RedirectAttributes attributes,@PathVariable String appNo,@PathVariable String flag) {
        String service = (String)request.getSession().getAttribute("SERVICE");
        consultantActionService.send2MyOrGroupTask(appNo,flag,getLoggedInUser().getUserID());
        attributes.addFlashAttribute("appNo", appNo);
        switch (service){
            case "NR":
                return "redirect:/admin/consultantNRAction";
            case "RC":
                return "redirect:/admin/consultantRCAction";
            case "CC":
                return "redirect:/admin/consultantCCAction";
            case "OS":
                return "redirect:/admin/consultantOSAction";
        }
        return "redirect:/admin/consultantNRAction";
    }
}
