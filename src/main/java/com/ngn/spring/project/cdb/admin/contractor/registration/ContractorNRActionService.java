package com.ngn.spring.project.cdb.admin.contractor.registration;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorTrainingDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAttachment;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

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
        responseMessage.setText("Contractor application number :<b>" + appNo + "</b> verified successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage approve(BigInteger appNo, String aRemarks, LoggedInUser loggedInUser)throws Exception {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        contractorNRActionDao.approve(contractorId, loggedInUser.getUserID(), aRemarks);

        String emailId = (String)commonService.getValue("crpcontractor","Email","ReferenceNo",appNo.toString());
        String mailContent = "Dear User,<br>Your application for application number : "+appNo.toString()+" is approved."+
                "<br>You may pay the required fee online through following link:<br>" +
                "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                "<br>Or You may visit our CDB counters to pay the fee. " +
                "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system. ";
        MailSender.sendMail(emailId, "cdb@gov.bt", null, mailContent, "Application approved");
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :<b>"+appNo+"</b> approved successfully.");
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
        contractor.setRegApprovedDate(loggedInUser.getServerDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        contractor.setHasNotification("0");
        calendar.add(Calendar.YEAR, 2);
        contractor.setRegExpiryDate(calendar.getTime());
        contractorNRActionDao.saveOrUpdate(contractor);

        paymentUpdateDTO.setContractorId(contractor.getContractorId());
        contractorNRActionDao.paymentUpdate(contractor.getContractorId(),loggedInUser.getUserID(),approvedApplicationStatusId,contractor.getCreatedBy());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :<b>"+paymentUpdateDTO.getAppNo().charAt(0)+"</b> Payment Approved. And CDB Number is: <b>"+paymentUpdateDTO.getCdbNo()+"</b>");
        String mailContent = "Dear User,Your application for application number : <b>"+paymentUpdateDTO.getAppNo().charAt(0)+"</b> is approved. And CDB Number is: <b>"+paymentUpdateDTO.getCdbNo()+"</b>"+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(contractor.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");

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
        //String cdbNo = contractorNRActionDao.getNextCDBNo();
        //return cdbNo;
        return contractorNRActionDao.getCDBNoFromAppNo(appNo);
        //return (String)commonService.getValue("crpcontractor","CDBNo","ReferenceNo",appNo);
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
