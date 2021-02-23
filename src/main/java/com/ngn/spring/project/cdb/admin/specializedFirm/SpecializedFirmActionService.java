package com.ngn.spring.project.cdb.admin.specializedFirm;

import bt.gov.g2c.aggregator.business.InvokePaymentWS;
import bt.gov.g2c.aggregator.dto.PaymentDTO;
import bt.gov.g2c.aggregator.dto.RequestDTO;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmAttachment;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirm;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.global.global.SmsSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Service
public class SpecializedFirmActionService extends BaseService {

    @Autowired
    private SpecializedFirmActionDao specializedFirmActionDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SpecializedFirmService specializedFirmService;

    @Autowired
    private SpecializedFirmRService cRenewalService;

    @Transactional(readOnly = true)
    public List gTaskList(String userId,String status,String service){
        return specializedFirmActionDao.gTaskList(userId,status,service);
    }

/*    @Transactional(readOnly = true)
    public ContractorFinal getContractor(String cdbNo){
        return  specializedFirmService.getContractorFinal(cdbNo);
    }*/

    @Transactional(readOnly = true)
    public ResponseMessage getSpecializedFirmData(String referenceNo,Character flag){
            SpecializedFirmInfoDTO specializedFirmDTO = new SpecializedFirmInfoDTO();
            SpecializedFirm specializedFirm = specializedFirmService.getSpecializedFirm(referenceNo);

     //   SpecializedFirmFinal specializedFirmFinal = cRenewalService.getSpecializedFirmFinal(specializedFirm.getCdbNo());
      //  specializedFirmDTO.setOldDzongkhag(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirmFinal.getRegDzongkhagId()).toString());
      //  specializedFirmDTO.setOldFirmName(specializedFirmFinal.getFirmName());
      //  specializedFirmDTO.setOldEstbAddress(specializedFirmFinal.getRegAddress());

        specializedFirmDTO.setOwnershipChangeRemarks(specializedFirm.getOwnershipChangeRemarks());
        specializedFirmDTO.setpGewogTxt(specializedFirm.getpGewogId());
        specializedFirmDTO.setpVillageTxt(specializedFirm.getpVillageId());
        specializedFirmDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", specializedFirm.getpCountryId()).toString());
        specializedFirmDTO.setSpecializedFirm(specializedFirm);

        if(flag != 'P'){
            List<CategoryClassDTO> categoryClassDTOs = specializedFirmActionDao.getFeeCategoryClass(specializedFirm.getCrpSpecializedTradeId());
            specializedFirmDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", specializedFirm.getOwnershipTypeId()).toString());
            specializedFirmDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", specializedFirm.getpCountryId()).toString());

            List<SpFirmHrDTO> specializedFirmHRs = getSpecializedFirmHRs(specializedFirm.getCrpSpecializedTradeId(),'B'); //B for both owner and hr
            List<EquipmentDTO> equipmentDTOs = getSpecializedFirmEQs(specializedFirm.getCrpSpecializedTradeId());
            List<SpFirmAttachment> cIncAttachment = getIncAttachment(specializedFirm.getCrpSpecializedTradeId());
            List<ApplicationHistoryDTO> appHistoryDTOs = specializedFirmActionDao.getAppHistoryDtl(specializedFirm.getCrpSpecializedTradeId());
            specializedFirmDTO.setSpFirmHRs(specializedFirmHRs);
            specializedFirmDTO.setEquipments(equipmentDTOs);
            specializedFirmDTO.setAppHistoryDTOs(appHistoryDTOs);
            specializedFirmDTO.setCategories(categoryClassDTOs);
            specializedFirmDTO.setIncAttachments(cIncAttachment);
        } else {
            List<CategoryClassDTO> categoryClassDTOs = specializedFirmActionDao.getFeeCategoryClass(specializedFirm.getCrpSpecializedTradeId());
            List<SpFirmHrDTO> specializedFirmHRs = specializedFirmActionDao.getSpecializedFirmHRs(specializedFirm.getCrpSpecializedTradeId(), 'O'); //B for both owner and hr
            List<ApplicationHistoryDTO> appHistoryDTOs = specializedFirmActionDao.getAppHistoryDtl(specializedFirm.getCrpSpecializedTradeId());
            specializedFirmDTO.setCategories(categoryClassDTOs);
            specializedFirmDTO.setSpFirmHRs(specializedFirmHRs);
            String cdbNo = specializedFirmActionDao.getNextCDBNo();
            specializedFirmDTO.setCdbNo("NULL");
            specializedFirmDTO.setAppHistoryDTOs(appHistoryDTOs);
        }

        if(specializedFirm.getpDzongkhagId() != null) {
            specializedFirmDTO.setpDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirm.getpDzongkhagId()).toString());
        }
            specializedFirmDTO.setEstDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirm.getRegDzongkhagId()).toString());
            specializedFirmDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", specializedFirm.getOwnershipTypeId()).toString());
            specializedFirmDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", specializedFirm.getpCountryId()).toString());

        responseMessage.setDto(specializedFirmDTO);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
 }

    @Transactional(readOnly = false)
    public ResponseMessage verify(BigInteger appNo,String vRemarks,LoggedInUser loggedInUser) {
        String specializedFirmId = (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo.toString());
        specializedFirmActionDao.verify(specializedFirmId,loggedInUser.getUserID(),vRemarks);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number :"+appNo+" verified successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage approve(BigInteger appNo, String aRemarks, LoggedInUser loggedInUser) {
        String applicationNumber = String.valueOf(appNo);
        SpecializedFirm specializedFirm = specializedFirmService.getSpecializedFirm(applicationNumber);

        specializedFirm.setRegApprovedDate(loggedInUser.getServerDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        specializedFirm.setHasNotification("0");
        calendar.add(Calendar.YEAR, 2);
        specializedFirm.setRegExpiryDate(calendar.getTime());
        specializedFirmActionDao.saveOrUpdate(specializedFirm);

        String specializedFirmId = (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo.toString());
        specializedFirmActionDao.approve(specializedFirmId, loggedInUser.getUserID(), aRemarks);

        List<CategoryClassDTO> categoryClassDTOs = specializedFirmActionDao.getFeeCategoryClass(specializedFirmId);

        BigDecimal tFeeAmount = BigDecimal.ZERO;
        for(int i =0; i<categoryClassDTOs.size();i++){
            tFeeAmount =tFeeAmount.add(categoryClassDTOs.get(i).getaAmount());
        }

        RequestDTO dto = new RequestDTO();
        dto.setApplicationNo(applicationNumber);
        dto.setAgencyCode("CDB");
        dto.setServiceName("New Registration of Specialized Firm");
        dto.setExpiryDate(null);

        ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
        PaymentDTO paymentdto = new PaymentDTO();
        //Integer amount = passportUploadDAO.getServiceFees(applicationNo);
        BigDecimal amount = (BigDecimal) commonService.getValue("crpspecializedtraderegistrationpayment","ApprovedAmount","CrpSpecializedTradeFinalId",specializedFirmId);
        paymentdto.setServiceFee(String.valueOf(tFeeAmount));
        paymentdto.setAccountHeadId("acc_head_id_here");
        paymentList.add(paymentdto);
        dto.setPaymentList(paymentList.toArray(new PaymentDTO[paymentList.size()]));
        System.out.println("Response from Aggregator: "+paymentdto.getServiceFee());
        ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
        InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
        boolean isSaved = invokews.insertPaymentDetailsOnApproval(dto);
        System.out.println("Response from Aggregator: "+isSaved);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number:"+appNo+" approved successfully.");

        String emailId = (String)commonService.getValue("crpconsultant","Email","ReferenceNo",appNo.toString());
        String phoneNumber = (String)commonService.getValue("crpspecializedtrade","MobileNo","ReferenceNo",appNo.toString());

        String mailContent = "Dear User,<br>Your application for application number : "+appNo.toString()+" is approved."+
                "<br>You may pay the required fee online through following link:<br>" +
                "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                "<br>Or You may visit our CDB counters to pay the fee. " +
                "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system. ";
        try {
            MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved for Payment");
            SmsSender.smsSender(phoneNumber, "cdb@gov.bt", null, mailContent, "Application approved for Payment");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage paymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser) throws Exception{
        SpecializedFirm specializedFirm = specializedFirmService.getSpecializedFirm(paymentUpdateDTO.getAppNo());
        String approvedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12003");
       // contractor.setAppStatusId(approvedApplicationStatusId);
        specializedFirm.setAppStatusId(ApplicationStatus.APPROVED.getCode());
        specializedFirm.setRegStatus("3");
        specializedFirm.setCdbNo(paymentUpdateDTO.getCdbNo());
        specializedFirm.setPaymentApprovedDate(loggedInUser.getServerDate());
        specializedFirm.setPaymentApproverUserId(loggedInUser.getUserID());
        specializedFirm.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
        specializedFirm.setHasNotification("0");
        specializedFirm.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        specializedFirm.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        specializedFirmActionDao.saveOrUpdate(specializedFirm);

        paymentUpdateDTO.setSpecializedFirmId(specializedFirm.getCrpSpecializedTradeId());
        specializedFirmActionDao.paymentUpdate(specializedFirm.getCrpSpecializedTradeId(),loggedInUser.getUserID(),approvedApplicationStatusId,specializedFirm.getCreatedBy());

        String phoneNumber = (String)commonService.getValue("crpspecializedtrade", "MobileNo", "ReferenceNo", paymentUpdateDTO.getAppNo().toString());

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number :"+paymentUpdateDTO.getAppNo().charAt(0)+" Payment Approved.And CDB number is:"+paymentUpdateDTO.getCdbNo());
        String mailContent = "Dear User,Your application for application number : "+paymentUpdateDTO.getAppNo().charAt(0)+" is approved.And CDB number is:"+paymentUpdateDTO.getCdbNo()+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
        SmsSender.smsSender(phoneNumber, "cdb@gov.bt", null, mailContent, "Application Payment approved");

        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage onlinepaymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser) throws Exception{
        SpecializedFirm specializedFirm = specializedFirmService.getSpecializedFirm(paymentUpdateDTO.getAppNo());
        String approvedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12003");
        // contractor.setAppStatusId(approvedApplicationStatusId);
        specializedFirm.setAppStatusId(ApplicationStatus.APPROVED.getCode());
        specializedFirm.setRegStatus("3");
        specializedFirm.setCdbNo(paymentUpdateDTO.getCdbNo());
        specializedFirm.setPaymentApprovedDate(loggedInUser.getServerDate());
        specializedFirm.setPaymentApproverUserId(loggedInUser.getUserID());
        specializedFirm.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
        specializedFirm.setHasNotification("0");
        specializedFirm.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        specializedFirm.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        specializedFirmActionDao.saveOrUpdate(specializedFirm);

        paymentUpdateDTO.setSpecializedFirmId(specializedFirm.getCrpSpecializedTradeId());
        specializedFirmActionDao.paymentUpdate(specializedFirm.getCrpSpecializedTradeId(),loggedInUser.getUserID(),approvedApplicationStatusId,specializedFirm.getCreatedBy());

        String phoneNumber = (String)commonService.getValue("crpspecializedtrade", "MobileNo", "ReferenceNo", paymentUpdateDTO.getAppNo().toString());

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number :"+paymentUpdateDTO.getAppNo().charAt(0)+" Payment Approved.And CDB number is:"+paymentUpdateDTO.getCdbNo());
        String mailContent = "Dear User,Your application for application number : "+paymentUpdateDTO.getAppNo().charAt(0)+" is approved.And CDB number is:"+paymentUpdateDTO.getCdbNo()+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
        SmsSender.smsSender(phoneNumber, "cdb@gov.bt", null, mailContent, "Application Payment approved");

        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage reject(BigInteger appNo, String rRemarks, LoggedInUser loggedInUser) {
        String applicationNumber = String.valueOf(appNo);
        SpecializedFirm specializedFirm = specializedFirmService.getSpecializedFirm(applicationNumber);
        String specializedFirmId = (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo.toString());
        String rejectedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12004");
        specializedFirmActionDao.reject(specializedFirmId, loggedInUser.getUserID(), rRemarks,rejectedApplicationStatusId);
        String mailContent = "Dear User,<br>Your application for application number : " + appNo + " is Rejected because of" + rRemarks+ "." +
                "<br>You may re-apply through following link:<br>" +
                "<a target='_blank' href='/cdb/public_access/renewal'>Click here for resubmission of an application</a>" ;
        try {
            MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Rejected");
            SmsSender.smsSender(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Rejected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("SpecializedFirm application number :"+appNo+" has been rejected.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage sendBack(BigInteger appNo, String sendRemarks,String appStatus, LoggedInUser loggedInUser) {
        String specializedFirmId = (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo.toString());
        //TODO send back notification
        specializedFirmActionDao.sendBack(specializedFirmId, loggedInUser.getUserID(), sendRemarks, appStatus);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("SpecializedFirm application number :"+appNo+" is send back successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String flag,String lockUserId) {
        if(flag.equals("M")){
            lockUserId = null;
        }
        specializedFirmActionDao.send2MyOrGroupTask(appNo,lockUserId);
    }

    public String getApplicationStatus(String appNo) {
        return (String)commonService.getValue("crpspecializedtrade","CmnApplicationRegistrationStatusId","ReferenceNo",appNo);
    }

    /**
     * To get the CDB no from application reference no
     * @param appNo  -- application number
     * @return cdb no
     */
    @Transactional(readOnly = true)
    public String getCDBNoFromAppNo(String appNo){
      //  String cdbNo = specializedFirmActionDao.getNextCDBNo();
      //  return cdbNo;
        return specializedFirmActionDao.getCDBNoFromAppNo(appNo);
       // return (String)commonService.getValue("crpspecializedtrade","SPNo","ReferenceNo",appNo);
    }

    @Transactional(readOnly = true)
    public String getNextCDBNo(String appNo){
          String cdbNo = specializedFirmActionDao.getNextCDBNo();
          return cdbNo;
    }

    @Transactional(readOnly = true)
    public List<SpFirmHrDTO> getSpecializedFirmHRs(String specializedFirmId,Character ownerOrPartner) {
        List<SpFirmHrDTO> specializedFirmHRs = specializedFirmActionDao.getSpecializedFirmHRs(specializedFirmId, ownerOrPartner);
        specializedFirmHRs.forEach(h->h.setHrAttachments(specializedFirmActionDao.getHRAttachments(h.getId())));
        return specializedFirmHRs;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getSpecializedFirmEQs(String contractorId) {
        List<EquipmentDTO> equipmentDTOs = specializedFirmActionDao.getEquipment(contractorId);
        equipmentDTOs.forEach(e->e.setEqAttachments(specializedFirmActionDao.getEQAttachments(e.getId())));
        return equipmentDTOs;
    }

    @Transactional(readOnly = true)
    private List<SpFirmAttachment> getIncAttachment(String crpSpecializedTradeId) {
        return  specializedFirmActionDao.getIncAttachment(crpSpecializedTradeId);
    }
}
