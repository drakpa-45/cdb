package com.ngn.spring.project.cdb.admin.contractor.cancellation;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
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
@RequestMapping("/admin/contractorCCAction")
public class ContractorCCActionController extends BaseController {
    @Autowired
    private ContractorNRActionService contractorNRActionService;

    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appNo")String appNo) {

        if(appNo == null || appNo.isEmpty()){ //if no appNo, redirect task list again
            model.remove("appNo");
            return "redirect:/admin/contractor";
        }
        model.addAttribute("appNo", appNo);
        model.addAttribute("cdbNo", contractorNRActionService.getCDBNoFromAppNo(appNo));
        String appStatus = contractorNRActionService.getApplicationStatus(appNo);
        return "admin/contractor/contractorCCAction";

    }

    @ResponseBody
    @RequestMapping(value = "/getContractorInfo", method = RequestMethod.GET)
    public ResponseMessage getContractorInfo(HttpServletRequest request, String appNo,Character flag) {
        return contractorNRActionService.getContractorData(appNo,flag);
    }

    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseMessage verify(HttpServletRequest request, BigInteger appNo, String vRemarks) {
        loggedInUser = gLoggedInUser(request);
        return contractorNRActionService.verify(appNo, vRemarks, loggedInUser);
    }

    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String documentPath) throws Exception{
        commonService.viewDownloadFile(documentPath, response);
    }


    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseMessage approve(HttpServletRequest request, BigInteger appNo, String remarks) throws Exception{
        loggedInUser = gLoggedInUser(request);
        return contractorNRActionService.approve(appNo, remarks, loggedInUser);
    }
}
