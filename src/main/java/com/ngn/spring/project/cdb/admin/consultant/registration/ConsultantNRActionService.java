package com.ngn.spring.project.cdb.admin.consultant.registration;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import com.ngn.spring.project.cdb.consultant.model.Consultant;
import com.ngn.spring.project.cdb.consultant.model.ConsultantFinal;
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
public class ConsultantNRActionService extends BaseService {

    @Autowired
    private ConsultantNRActionDao consultantActionDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ConsultantNRService consultantService;

    @Transactional(readOnly = true)
    public List gTaskList(String userId,String status,String service){
        return consultantActionDao.gTaskList(userId, status, service);
    }

    @Transactional(readOnly = true)
    public List gMyTaskList(String userId){
        return consultantActionDao.gMyTaskList(userId);
    }

    @Transactional(readOnly = true)
    public ConsultantFinal getConsultant(String cdbNo){
        return  consultantService.getConsultantFinal(cdbNo);
    }

        @Transactional(readOnly = true)
    public ResponseMessage getConsultantData(String referenceNo,Character flag){
            ConsultantInfoDTO consultantDTO = new ConsultantInfoDTO();
        Consultant consultant = consultantService.getConsultant(referenceNo);
            consultantDTO.setConsultant(consultant);

        if(flag != 'P'){
            List<CategoryClassDTO> categoryClassDTOs = consultantActionDao.getCategoryClass(consultant.getConsultantId());
            consultantDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", consultant.getOwnershipTypeId()).toString());
            consultantDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", consultant.getpCountryId()).toString());
            if(consultant.getRegDzongkhagId() != null) {
                consultantDTO.setpDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultant.getRegDzongkhagId()).toString());
            }
            //TODO ::save gewog and village their name or ids
            consultantDTO.setEstDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultant.getRegDzongkhagId()).toString());

            List<ConsultantHrDTO> consultantHRs = consultantActionDao.getConsultantHRs(consultant.getConsultantId(),'B');  //B for both owner and hr
            consultantHRs.forEach(h->h.setHrAttachments(consultantActionDao.getHRAttachments(h.getId())));

            List<EquipmentDTO> equipmentDTOs = consultantActionDao.getEquipment(consultant.getConsultantId());
            equipmentDTOs.forEach(e->e.setEqAttachments(consultantActionDao.getEQAttachments(e.getId())));

            List<ApplicationHistoryDTO> appHistoryDTOs = consultantActionDao.getAppHistoryDtl(consultant.getConsultantId());
            consultantDTO.setConsultantHRs(consultantHRs);
            consultantDTO.setEquipments(equipmentDTOs);
            consultantDTO.setAppHistoryDTOs(appHistoryDTOs);
            consultantDTO.setCategories(categoryClassDTOs);
        } else{
            List<CategoryClassDTO> categoryClassDTOs = consultantActionDao.getCategoryClass(consultant.getConsultantId());
            List<ConsultantHrDTO> consultantHRs = consultantActionDao.getConsultantHRs(consultant.getConsultantId(), 'O');  //B for both owner and hr
            List<ApplicationHistoryDTO> appHistoryDTOs = consultantActionDao.getAppHistoryDtl(consultant.getConsultantId());
            consultantDTO.setCategories(categoryClassDTOs);
            consultantDTO.setAppHistoryDTOs(appHistoryDTOs);
            consultantDTO.setConsultantHRs(consultantHRs);
            String cdbNo = consultantActionDao.getNextCDBNo();
            consultantDTO.setCdbNo(cdbNo);
        }
            consultantDTO.setEstDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultant.getRegDzongkhagId()).toString());
            consultantDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", consultant.getOwnershipTypeId()).toString());
            consultantDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", consultant.getpCountryId()).toString());

        responseMessage.setDto(consultantDTO);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage verify(BigInteger appNo,String vRemarks,LoggedInUser loggedInUser) {
        String consultantId = (String)commonService.getValue("crpconsultant","CrpConsultantId","ReferenceNo",appNo.toString());
        consultantActionDao.verify(consultantId,loggedInUser.getUserID(),vRemarks);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Consultant application number :" + appNo + " is verified successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage approve(BigInteger appNo, String aRemarks, LoggedInUser loggedInUser) {
        String consultantId = (String)commonService.getValue("crpconsultant","CrpConsultantId","ReferenceNo",appNo.toString());
        consultantActionDao.approve(consultantId, loggedInUser.getUserID(), aRemarks);

        String emailId = (String)commonService.getValue("crpconsultant","Email","ReferenceNo",appNo.toString());
        String mailContent = "Dear User,<br>Your application for application number : "+appNo.toString()+" is approved."+
                "<br>You may pay the required fee online through following link:<br>" +
                "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                "<br>Or You may visit our CDB counters to pay the fee. " +
                "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system. ";
        try {
            MailSender.sendMail(emailId,"cdb@gov.bt",null,mailContent,"Application approved for payment");
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Consultant application number :"+appNo+" is approved successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage paymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser) throws Exception{
        Consultant consultant = consultantService.getConsultant(paymentUpdateDTO.getAppNo());
        String approvedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12003");
        consultant.setAppStatusId(approvedApplicationStatusId);
        consultant.setRegStatus("3");
        consultant.setCdbNo(paymentUpdateDTO.getCdbNo());
        consultant.setPaymentApprovedDate(loggedInUser.getServerDate());
        consultant.setPaymentApproverUserId(loggedInUser.getUserID());
        consultant.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
        consultant.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        consultant.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        consultant.setRegApprovedDate(loggedInUser.getServerDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        consultant.setHasNotification("0");
        calendar.add(Calendar.YEAR, 2);
        consultant.setRegExpiryDate(calendar.getTime());
        consultantActionDao.saveOrUpdate(consultant);

        paymentUpdateDTO.setConsultantId(consultant.getConsultantId());
        consultantActionDao.paymentUpdate(consultant.getConsultantId(),loggedInUser.getUserID()
                , approvedApplicationStatusId,loggedInUser.getUserID());//consultant.getCreatedBy()
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Consultant application number :"+paymentUpdateDTO.getAppNo()+" Payment Approved");
        String mailContent = "Dear User,Your application for application number : "+paymentUpdateDTO.getAppNo()+" is approved."+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(consultant.getRegEmail(),"cdb@gov.bt",null,mailContent,"Application approved");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage reject(BigInteger appNo, String rRemarks, LoggedInUser loggedInUser) {
        String emailId = (String)commonService.getValue("crpconsultant","Email","ReferenceNo",appNo.toString());
        String consultantId = (String)commonService.getValue("crpconsultant","CrpConsultantId","ReferenceNo",appNo.toString());
        String rejectedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12004");
        consultantActionDao.reject(consultantId, loggedInUser.getUserID(), rRemarks,rejectedApplicationStatusId);
        String mailContent = "Dear User,<br>Your application for application number : " + appNo + " is Rejected because of" + rRemarks+ "." +
                "<br>You may re-apply through following link:<br>" +
                "<a target='_blank' href='/cdb/public_access/renewal'>Click here for resubmission of an application</a>" ;
        try {
            MailSender.sendMail(emailId, "cdb@gov.bt", null, mailContent, "Application Rejected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Consultant application number :"+appNo+" has been rejected.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String flag,String lockUserId) {
        if(flag.equals("M")){
            lockUserId = null;
        }
        consultantActionDao.send2MyOrGroupTask(appNo,lockUserId);
    }

    public String getApplicationStatus(String appNo) {
        return (String)commonService.getValue("crpconsultant","CmnApplicationRegistrationStatusId","ReferenceNo",appNo);
    }

    @Transactional(readOnly = false)
    public ResponseMessage sendBack(BigInteger appNo, String sendRemarks,String appStatus, LoggedInUser loggedInUser) {
        String consultantId = (String)commonService.getValue("crpconsultant","CrpConsultantId","ReferenceNo",appNo.toString());
        //TODO send back notification
        consultantActionDao.sendBack(consultantId, loggedInUser.getUserID(), sendRemarks, appStatus);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Consultant application number :"+appNo+" is send back successfully.");
        return responseMessage;
    }

    /**
     * To get the CDB no from application reference no
     * @param appNo  -- application number
     * @return cdb no
     */

    @Transactional(readOnly = true)
    public String getCDBNoFromAppNo(String appNo){
        return (String)commonService.getValue("crpconsultant","CDBNo","ReferenceNo",appNo);
    }

    @Transactional(readOnly = true)
    public List<ConsultantHrDTO> getConsultantHRs(String consultantId,Character ownerOrPartner) {
        List<ConsultantHrDTO> contractorHRs = consultantActionDao.getConsultantHRs(consultantId, ownerOrPartner);
        contractorHRs.forEach(h->h.setHrAttachments(consultantActionDao.getHRAttachments(h.getId())));
        return contractorHRs;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getConsultantEQs(String contractorId) {
        List<EquipmentDTO> equipmentDTOs = consultantActionDao.getEquipment(contractorId);
        equipmentDTOs.forEach(e->e.setEqAttachments(consultantActionDao.getEQAttachments(e.getId())));
        return equipmentDTOs;
    }
}
