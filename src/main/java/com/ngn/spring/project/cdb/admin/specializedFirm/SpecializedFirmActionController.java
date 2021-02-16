package com.ngn.spring.project.cdb.admin.specializedFirm;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.admin.dto.PaymentUpdateDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
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
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin/specializedFirm/action")
public class SpecializedFirmActionController extends BaseController {

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

        String appStatus = specializedFirmActionService.getApplicationStatus(appNo);
        model.addAttribute("modeOfPayment", commonService.getModePayment());
        if(appStatus.equals(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode())){
            return "admin/specializedFirm/specializedFirm_payment";
        }
        return "admin/specializedFirm/specializedFirm_action";
    }

    @ResponseBody
    @RequestMapping(value = "/getSpecializedFirmInfo", method = RequestMethod.GET)
    public ResponseMessage getSpecializedFirmInfo(HttpServletRequest request, String appNo,Character flag) {
        return specializedFirmActionService.getSpecializedFirmData(appNo,flag);
    }

    @ResponseBody
    @RequestMapping(value ="/checkEquipment", method = RequestMethod.GET)
    public ResponseMessage checkEquipment(String regNo, String serviceName){
        return commonService.checkEquipment(regNo, serviceName);
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

    @ResponseBody
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ResponseMessage reject(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return specializedFirmActionService.reject(appNo, remarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/paymentUpdate", method = RequestMethod.POST)
    public ResponseMessage paymentUpdate(HttpServletRequest request, PaymentUpdateDTO paymentUpdateDTO) throws Exception{
        loggedInUser = gLoggedInUser(request);
        String appNo = request.getParameter("appNo");
        String cdbNo = specializedFirmActionService.getNextCDBNo(appNo);
        paymentUpdateDTO.setCdbNo(cdbNo);
        return specializedFirmActionService.paymentUpdate(paymentUpdateDTO, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/sendBack", method = RequestMethod.POST)
    public ResponseMessage sendBack(HttpServletRequest request, BigInteger appNo, String remarks) throws Exception{
        loggedInUser = gLoggedInUser(request);
        String appStatus = null;
        String ver = null;
        if(request.isUserInRole("ROLE_APPROVER")) {
            appStatus= ApplicationStatus.UNDER_PROCESS.getCode();
        }else if(request.isUserInRole("ROLE_PAYMENT")){
            appStatus= ApplicationStatus.VERIFIED.getCode();
        }
        return specializedFirmActionService.sendBack(appNo, remarks,appStatus,loggedInUser);
    }

}
