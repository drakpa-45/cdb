package com.ngn.spring.project.cdb.admin.specializedFirm.cancellation;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.admin.specializedFirm.SpecializedFirmActionService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/2/2020.
 * Description: Contractor cancellation of certificate controller -- CC (Cancellation of Certificate)
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin/specializedFirmCCAction")
public class SpecializedFirmCCActionController extends BaseController {
    @Autowired
    private SpecializedFirmActionService specializedFirmActionService;

    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appNo")String appNo) {

        if(appNo == null || appNo.isEmpty()){ //if no appNo, redirect task list again
            model.remove("appNo");
            return "redirect:/admin/specializedFirm";
        }
        model.addAttribute("appNo", appNo);
        model.addAttribute("cdbNo", specializedFirmActionService.getCDBNoFromAppNo(appNo));
        String appStatus = specializedFirmActionService.getApplicationStatus(appNo);
        return "admin/specializedFirm/specializedFirmCCAction";

    }

    @ResponseBody
    @RequestMapping(value = "/getSpecializedFirmInfo", method = RequestMethod.GET)
    public ResponseMessage getSpecializedFirmInfo(HttpServletRequest request, String appNo,Character flag) {
        return specializedFirmActionService.getSpecializedFirmData(appNo,flag);
    }

    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseMessage verify(HttpServletRequest request, BigInteger appNo, String vRemarks) {
        loggedInUser = gLoggedInUser(request);
        return specializedFirmActionService.verify(appNo, vRemarks, loggedInUser);
    }

    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String documentPath) throws Exception{
        commonService.viewDownloadFile(documentPath, response);
    }


    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseMessage approve(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return specializedFirmActionService.approve(appNo, remarks, loggedInUser);
    }
}
