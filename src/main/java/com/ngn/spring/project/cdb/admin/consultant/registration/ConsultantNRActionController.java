package com.ngn.spring.project.cdb.admin.consultant.registration;

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
import java.util.List;

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
@RequestMapping("/admin/consultantNRAction")
public class ConsultantNRActionController extends BaseController {

    @Autowired
    private ConsultantNRActionService consultantActionService;

    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appNo")String appNo) {
        if(appNo == null || appNo.isEmpty()){
            model.remove("appNo");
            return "redirect:/admin/consultant";
        }
        //model.addAttribute("appNo", appNo);
     //   model.addAttribute("cdbNo", consultantActionService.getCDBNoFromAppNo(appNo));
        String appStatus = consultantActionService.getApplicationStatus(appNo);
        model.addAttribute("modeOfPayment", commonService.getModePayment());
        if(appStatus.equals(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode())){
            return "admin/consultant/consultantNRpayment";
        }
        return "admin/consultant/consultantNRAction";
    }

    @ResponseBody
    @RequestMapping(value = "/getConsultantInfo", method = RequestMethod.GET)
    public ResponseMessage getConsultantInfo(HttpServletRequest request, String appNo,Character flag,ModelMap model) {
        ResponseMessage consultantData = consultantActionService.getConsultantData(appNo, flag);
        return consultantData;
    }

    @ResponseBody
    @RequestMapping(value = "/checkEquipment", method = RequestMethod.GET)
    public Object checkEquipment(HttpServletRequest request, String registrationNo, String serviceName) throws Exception{
        return commonService.checkEquipment(registrationNo,serviceName);
    }

    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseMessage verify(HttpServletRequest request, BigInteger appNo, String vRemarks) {
        loggedInUser = gLoggedInUser(request);
        return consultantActionService.verify(appNo, vRemarks, loggedInUser);
    }

    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String documentPath) throws Exception{
        commonService.viewDownloadFile(documentPath, response);
    }

    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseMessage approve(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return consultantActionService.approve(appNo, remarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ResponseMessage reject(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return consultantActionService.reject(appNo, remarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/paymentUpdate", method = RequestMethod.POST)
    public ResponseMessage paymentUpdate(ModelMap model, HttpServletRequest request, PaymentUpdateDTO paymentUpdateDTO) throws Exception{
        loggedInUser = gLoggedInUser(request);
        String appNo = request.getParameter("appNo");
        String cdbNo = consultantActionService.getNextCDBNo(appNo);
        paymentUpdateDTO.setCdbNo(cdbNo);
        return consultantActionService.paymentUpdate(paymentUpdateDTO, loggedInUser);
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
        return consultantActionService.sendBack(appNo, remarks,appStatus,loggedInUser);
    }

    @RequestMapping(value = "/validateCorporateCidNo", method = RequestMethod.GET)
    public ResponseMessage validateCorporateCidNo(HttpServletRequest request,String cidNo) {
      //  String cidNo = request.getParameter("cidNo");
        try{
          //  ResponseMessage validateCorporateCidNo=commonService.validateCorporateCidNo(request,cidNo);
           // return validateCorporateCidNo;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
        return null;
    }
}
