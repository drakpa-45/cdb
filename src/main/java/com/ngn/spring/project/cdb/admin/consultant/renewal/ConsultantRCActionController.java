package com.ngn.spring.project.cdb.admin.consultant.renewal;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.admin.consultant.registration.ConsultantNRActionService;
import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
import com.ngn.spring.project.cdb.admin.contractor.renewal.ContractorRCActionService;
import com.ngn.spring.project.cdb.admin.dto.PaymentUpdateDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
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
import java.util.List;

/**
 * ====================================================================
 * Created by Pema Drakpa on 8/2/2020.
 * Description: consultant renewal of certificate controller
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin/consultantRCAction")
public class ConsultantRCActionController extends BaseController {

    @Autowired
    private ConsultantRCActionService cRCActionService;
    @Autowired
    private ConsultantNRActionService cNRActionService;
    @Autowired
    private CommonService commonService;
    @Autowired
    ConsultantRCService consultantRCService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appNo")String appNo) {

        if(appNo == null || appNo.isEmpty()){ //if no appNo, redirect task list again
            model.remove("appNo");
            return "redirect:/admin/consultant";
        }
        model.addAttribute("appNo", appNo);
        model.addAttribute("cdbNo", cNRActionService.getCDBNoFromAppNo(appNo));
        String appStatus = cNRActionService.getApplicationStatus(appNo);
        model.addAttribute("modeOfPayment", commonService.getModePayment());
        if (appStatus.equals(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode())) {
            return "admin/consultant/consultantRCPayment";
        } else {
            return "admin/consultant/consultantRCAction";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getAppliedServices", method = RequestMethod.GET)
    public List getAppliedServices(HttpServletRequest request, String appNo) {
        return cRCActionService.getAppliedServices(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "/getConsultantInfo", method = RequestMethod.GET)
    public ResponseMessage getConsultantInfo(HttpServletRequest request, String appNo,Character flag,ModelMap model) {
        ResponseMessage consultantData = cNRActionService.getConsultantData(appNo, flag);
        return consultantData;
    }

    @ResponseBody
    @RequestMapping(value = "/getConsultantInfoOwner", method = RequestMethod.GET)
    public Object getConsultantInfoOwner(HttpServletRequest request, String appNo) throws Exception{
        return cRCActionService.getConsultantInfoOwner(appNo);
    }

    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String documentPath) throws Exception{
        commonService.viewDownloadFile(documentPath, response);
    }

    @ResponseBody
    @RequestMapping(value = "/getHrs", method = RequestMethod.GET)
    public Object getHrs(HttpServletRequest request, String appNo) throws Exception{
        return cRCActionService.getHrsExistingAndNew(appNo);
    }

    @ResponseBody
    @RequestMapping(value = "/getProposedCategories", method = RequestMethod.GET)
    public ResponseMessage getProposedCategories(HttpServletRequest request, String appNo) throws Exception{
        ResponseMessage consultantData =cRCActionService.getProposedCategories(appNo);
        return consultantData;
       // return null;
    }

    @ResponseBody
    @RequestMapping(value = "/getEQs", method = RequestMethod.GET)
    public Object getEQs(HttpServletRequest request, String appNo) throws Exception{
        return cRCActionService.getEQs(appNo);
    }

/*    @ResponseBody
    @RequestMapping(value = "/getEmployeeDetailsFromCDB", method = RequestMethod.GET)
    public List getEmployeeDetailsFromCDB(HttpServletRequest request, String cidNo) throws Exception{
        return commonService.getEmployeeDetailsFromCDB(cidNo);
    }*/


    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseMessage verify(HttpServletRequest request, BigInteger appNo, String vRemarks) {
        loggedInUser = gLoggedInUser(request);
        return cNRActionService.verify(appNo, vRemarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseMessage approve(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return cNRActionService.approve(appNo, remarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ResponseMessage reject(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return cNRActionService.reject(appNo, remarks, loggedInUser);
    }


    @ResponseBody
    @RequestMapping(value = "/sendNotification", method = RequestMethod.GET)
    public ResponseMessage sendNotification(HttpServletRequest request, String cdbNo, String email) {
        loggedInUser = gLoggedInUser(request);
        return consultantRCService.sendNotification(cdbNo, email, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/paymentUpdate", method = RequestMethod.POST)
    public ResponseMessage paymentUpdate(HttpServletRequest request, PaymentUpdateDTO paymentUpdateDTO) throws Exception{
        loggedInUser = gLoggedInUser(request);
        return cRCActionService.paymentUpdate(paymentUpdateDTO, loggedInUser);
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
        return cNRActionService.sendBack(appNo, remarks,appStatus,loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/getServicesFee", method = RequestMethod.GET)
    public List getServicesFee(HttpServletRequest request, BigInteger appNo, String remarks) throws Exception{
        //loggedInUser = gLoggedInUser(request);
        return cRCActionService.getServicesFee(appNo.intValue());
    }

    @ResponseBody
    @RequestMapping(value ="/getConsultantFinal", method = RequestMethod.GET)
    public Object getConsultantFinal(HttpServletRequest request, String appNo){
        String cdbNo = cNRActionService.getCDBNoFromAppNo(appNo);
        return consultantRCService.getConsultantFinal(cdbNo);
    }
}
