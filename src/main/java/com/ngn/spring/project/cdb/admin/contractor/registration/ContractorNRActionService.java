package com.ngn.spring.project.cdb.admin.contractor.registration;

import bt.gov.g2c.aggregator.business.InvokePaymentWS;
import bt.gov.g2c.aggregator.dto.PaymentDTO;
import bt.gov.g2c.aggregator.dto.RequestDTO;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorTrainingDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAttachment;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCDao;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
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
import java.util.*;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class ContractorNRActionService extends BaseService {

    @Autowired
    private ContractorNRActionDao contractorNRActionDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ContractorNRService contractorNRService;
    @Autowired
    private ContractorRCDao contractorRCDao;

    @Autowired
    private ContractorRCService cRenewalService;

    @Transactional(readOnly = true)
    public List gTaskList(String userId,String status,String service){
        return contractorNRActionDao.gTaskList(userId,status,service);
    }

    @Transactional(readOnly = true)
    public ResponseMessage getContractorData(String referenceNo,Character flag){
        ContractorInfoDTO contractorDTO = new ContractorInfoDTO();
        Contractor contractor = contractorNRService.getContractor(referenceNo);
      // ContractorFinal contractorFinal = cRenewalService.getContractorFinal(contractor.getCdbNo());
      //  contractorDTO.setOldDzongkhag(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractorFinal.getRegDzongkhagId()).toString());
      //  contractorDTO.setOldFirmName(contractorFinal.getFirmName());
      //  contractorDTO.setOldEstbAddress(contractorFinal.getEstAddress());

        contractorDTO.setpGewogTxt(contractor.getpGewog());
        contractorDTO.setpVillageTxt(contractor.getpVillage());
        contractorDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", contractor.getpCountryId()).toString());
        contractorDTO.setContractor(contractor);

        if(flag != 'P'){
            List<CategoryClassDTO> categoryClassDTOs = contractorNRActionDao.getFeeCategoryClass(contractor.getContractorId());
            contractorDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", contractor.getOwnershipTypeId()).toString());
            contractorDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", contractor.getpCountryId()).toString());
            if(contractor.getpDzongkhagId() != null) {
                contractorDTO.setpDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractor.getpDzongkhagId()).toString());
            }
            List<ContractorHrDTO> contractorHRs = getContractorHRs(contractor.getContractorId(),'B'); //B for both owner and hr
            List<EquipmentDTO> contractorEQs = getContractorEQs(contractor.getContractorId());
            List<ContractorAttachment> cIncAttachment = getIncAttachment(contractor.getId());
            List<ApplicationHistoryDTO> appHistoryDTOs = contractorNRActionDao.getAppHistoryDtl(contractor.getContractorId());
            contractorDTO.setContractorHRs(contractorHRs);
            contractorDTO.setEquipments(contractorEQs);
            contractorDTO.setAppHistoryDTOs(appHistoryDTOs);
            contractorDTO.setCategories(categoryClassDTOs);
            contractorDTO.setIncAttachments(cIncAttachment);
        }else{
            List<CategoryClassDTO> categoryClassDTOs = contractorNRActionDao.getFeeCategoryClass(contractor.getContractorId());
            List<ContractorHrDTO> contractorHRs = contractorNRActionDao.getContractorHRs(contractor.getContractorId(), 'O'); //B for both owner and hr
            List<ApplicationHistoryDTO> appHistoryDTOs = contractorNRActionDao.getAppHistoryDtl(contractor.getContractorId());
            contractorDTO.setCategories(categoryClassDTOs);
            contractorDTO.setContractorHRs(contractorHRs);
            contractorDTO.setAppHistoryDTOs(appHistoryDTOs);
            String cdbNo = contractorNRActionDao.getNextCDBNo();
            contractorDTO.setCdbNo("NULL");
        }
        if(contractor.getpDzongkhagId() != null) {
            contractorDTO.setpDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractor.getpDzongkhagId()).toString());
        }
        contractorDTO.setEstDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractor.getRegDzongkhagId()).toString());
        contractorDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", contractor.getOwnershipTypeId()).toString());
        contractorDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", contractor.getpCountryId()).toString());
        responseMessage.setDto(contractorDTO);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage verify(BigInteger appNo,String vRemarks,LoggedInUser loggedInUser) {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        contractorNRActionDao.verify(contractorId, loggedInUser.getUserID(), vRemarks);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :" + appNo + " verified successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage approve(BigInteger appNo, String aRemarks, LoggedInUser loggedInUser)throws Exception {
        String applicationNo = String.valueOf(appNo);
        Contractor contractor = contractorNRService.getContractor(applicationNo);
        contractor.setRegApprovedDate(loggedInUser.getServerDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        if(contractor.getpCountryId().equalsIgnoreCase("8f897032-c6e6-11e4-b574-080027dcfac6")){ //if bhutanese then the certificate validity year is 2 else 1.
            calendar.add(Calendar.YEAR, 2);
        }else{
            calendar.add(Calendar.YEAR, 1);
        }
        contractor.setRegExpiryDate(calendar.getTime());
        contractor.setHasNotification("0");
        contractorNRActionDao.saveOrUpdate(contractor);

        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        contractorNRActionDao.approve(contractorId, loggedInUser.getUserID(), aRemarks);

        RequestDTO dto = new RequestDTO();
        dto.setApplicationNo(applicationNo);
        dto.setAgencyCode("CDB");
        dto.setServiceName("New Registration of Contractor");
        dto.setExpiryDate(null);
        ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
        PaymentDTO paymentdto = new PaymentDTO();
        //Integer amount = passportUploadDAO.getServiceFees(applicationNo);
        BigDecimal amount = (BigDecimal) commonService.getValue("crpcontractorregistrationpayment","ApprovedAmount","CrpContractorFinalId",contractorId);
        paymentdto.setServiceFee(String.valueOf(amount));

        paymentdto.setAccountHeadId("Choneywangmo@cdb.gov.bt");
        paymentList.add(paymentdto);
        dto.setPaymentList(paymentList.toArray(new PaymentDTO[paymentList.size()]));
        System.out.println("Response from Aggregator: "+paymentdto.getServiceFee());
        ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
        InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
        boolean isSaved = invokews.insertPaymentDetailsOnApproval(dto);
        System.out.println("Response from Aggregator: "+isSaved);

         List<ServiceFeeDTO> serviceFeeList = contractorRCDao.getServicesFee(Integer.valueOf(applicationNo));

        String emailId = (String)commonService.getValue("crpcontractor","Email","ReferenceNo",appNo.toString());
        String phoneNumber = (String)commonService.getValue("crpcontractor","MobileNo","ReferenceNo",appNo.toString());
        String mailContent = "Dear User,<br>Your application number : "+appNo.toString()+" is approved for payment."+
                "<br>You may pay the required fee online through following link:<br>" +
                "<a target='_blank' href='https://tinyurl.com/y3m7wa3c'>https://tinyurl.com/y3m7wa3c</a>" +
                "<br>Or You may visit our CDB counters to pay the fee. " +
                "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system."+
                "<br><br><html>"+
                "<div class='card-body'>"+
                "<table width='1000px' cellpadding=\"1\" cellspacing=\"1\" border=\"1\" style=\"background: green border-collapse: collapse\" class='table table-bordered table-condensed table-striped'" +
                "<thead>" +
                "<tr>"+
                "<th style='width: 45%'>Service Name</th>" +
                "<th style='width: 25%'>Fees (Nu.)</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>"+
                "<td></td>" +
                "<td></td>" +
                "</tr>"+
                "</tbody>" +
                "<tfoot>" +
                "</tfoot>" +
                "</table>"+
                "</div>"+
                "</html>";
        String smsContent = "Dear User,<br>Your application number : "+appNo.toString()+" is approved for payment."+
                "Thank you," +
                "(CDB)";
        MailSender.sendMail("pemadrkpa45@gmail.com", "cdb@gov.bt", null, mailContent, "Application approved for Payment");
        SmsSender.smsSender(phoneNumber, "cdb@gov.bt", null, smsContent, "Application approved for Payment");
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :" + appNo + " approved successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage paymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser) throws Exception{
        Contractor contractor = contractorNRService.getContractor(paymentUpdateDTO.getAppNo());
        String approvedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12003");
       // contractor.setAppStatusId(approvedApplicationStatusId);
        contractor.setAppStatusId(ApplicationStatus.APPROVED.getCode());
        contractor.setRegStatus("3");
        contractor.setCdbNo(paymentUpdateDTO.getCdbNo());
        contractor.setPaymentApprovedDate(loggedInUser.getServerDate());
        contractor.setPaymentApproverUserId(loggedInUser.getUserID());
        contractor.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
       //contractor.setLockedByUserId("null");
        contractor.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        contractor.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        contractor.setHasNotification("0");

        contractorNRActionDao.saveOrUpdate(contractor);

        paymentUpdateDTO.setContractorId(contractor.getContractorId());
        contractorNRActionDao.paymentUpdate(contractor.getContractorId(),loggedInUser.getUserID(),approvedApplicationStatusId,contractor.getCreatedBy());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+paymentUpdateDTO.getAppNo().charAt(0)+" Payment Approved. And CDB Number is: "+paymentUpdateDTO.getCdbNo()+"");
        String mailContent = "Dear User,Your application for application number : <b>"+paymentUpdateDTO.getAppNo().charAt(0)+"</b> is approved. And CDB Number is: <b>"+paymentUpdateDTO.getCdbNo()+"</b>"+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(contractor.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");
        SmsSender.smsSender(contractor.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage reject(BigInteger appNo, String rRemarks, LoggedInUser loggedInUser) {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        String rejectedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12004");
        contractorNRActionDao.reject(contractorId, loggedInUser.getUserID(), rRemarks,rejectedApplicationStatusId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+appNo+" has been rejected.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage sendBack(BigInteger appNo, String sendRemarks,String appStatus, LoggedInUser loggedInUser) {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        //TODO send back notification
        contractorNRActionDao.sendBack(contractorId, loggedInUser.getUserID(), sendRemarks, appStatus);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+appNo+" is send back successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String flag,String lockUserId) {
        if(flag.equals("M")){
            lockUserId = null;
        }
        contractorNRActionDao.send2MyOrGroupTask(appNo,lockUserId);
    }

    public String getApplicationStatus(String appNo) {
        return (String)commonService.getValue("crpcontractor","CmnApplicationRegistrationStatusId","ReferenceNo",appNo);
    }

    /**
     * To get the CDB no from application reference no
     * @param appNo  -- application number
     * @return cdb no
     */
    @Transactional(readOnly = true)
    public String getCDBNoFromAppNo(String appNo){
        return contractorNRActionDao.getCDBNoFromAppNo(appNo);
        //return (String)commonService.getValue("crpcontractor","CDBNo","ReferenceNo",appNo);
}

    @Transactional(readOnly = true)
    public String getNextCDBNo(String appNo){
        String cdbNo = contractorNRActionDao.getNextCDBNo();
        return cdbNo;
    }

    @Transactional(readOnly = true)
    public List getFeeCategoryClass(String contractorId){
        return contractorNRActionDao.getFeeCategoryClass(contractorId);
    }

    @Transactional(readOnly = true)
    public List<ContractorHrDTO> getContractorHRs(String contractorId,Character ownerOrPartner) {
        List<ContractorHrDTO> contractorHRs = contractorNRActionDao.getContractorHRs(contractorId,ownerOrPartner);
        contractorHRs.forEach(h->h.setHrAttachments(contractorNRActionDao.getHRAttachments(h.getId())));
        return contractorHRs;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getContractorEQs(String contractorId) {
        List<EquipmentDTO> equipmentDTOs = contractorNRActionDao.getEquipment(contractorId);
        equipmentDTOs.forEach(e->e.setEqAttachments(contractorNRActionDao.getEQAttachments(e.getId())));
        return equipmentDTOs;
    }

    @Transactional(readOnly = true)
    public List<ContractorAttachment> getIncAttachment(String contractorId) {
        return  contractorNRActionDao.getIncAttachment(contractorId);
    }

}
