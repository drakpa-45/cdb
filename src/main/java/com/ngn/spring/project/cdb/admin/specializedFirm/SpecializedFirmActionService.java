package com.ngn.spring.project.cdb.admin.specializedFirm;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmAttachment;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirm;
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
            specializedFirmDTO.setSpecializedFirm(specializedFirm);

        if(flag != 'P'){
            List<CategoryClassDTO> categoryClassDTOs = specializedFirmActionDao.getFeeCategoryClass(specializedFirm.getCrpSpecializedTradeId());
            specializedFirmDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", specializedFirm.getOwnershipTypeId()).toString());
            specializedFirmDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", specializedFirm.getpCountryId()).toString());
            if(specializedFirm.getpDzongkhagId() != null) {
                specializedFirmDTO.setpDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirm.getRegDzongkhagId()).toString());
            }
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
            specializedFirmDTO.setCdbNo(cdbNo);
            specializedFirmDTO.setAppHistoryDTOs(appHistoryDTOs);
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
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String CLICK_HERE_TO_PAY =resourceBundle1.getString("G2CPaymentAggregatorStg.endPointURL");

        String specializedFirmId = (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo.toString());
        specializedFirmActionDao.approve(specializedFirmId, loggedInUser.getUserID(), aRemarks);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number:"+appNo+" approved successfully.");

        String emailId = (String)commonService.getValue("crpconsultant","Email","ReferenceNo",appNo.toString());
        String mailContent = "Dear User,<br>Your application for application number : "+appNo.toString()+" is approved."+
                "<br>You may pay the required fee online through following link:<br>" +
                "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                "<br>Or You may visit our CDB counters to pay the fee. " +
                "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system. ";
        try {
            MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
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
       //contractor.setLockedByUserId("null");
        specializedFirm.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        specializedFirm.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        specializedFirm.setRegApprovedDate(loggedInUser.getServerDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        specializedFirm.setHasNotification("0");
        calendar.add(Calendar.YEAR, 2);
        specializedFirm.setRegExpiryDate(calendar.getTime());
        specializedFirmActionDao.saveOrUpdate(specializedFirm);

        paymentUpdateDTO.setSpecializedFirmId(specializedFirm.getCrpSpecializedTradeId());
        specializedFirmActionDao.paymentUpdate(specializedFirm.getCrpSpecializedTradeId(),loggedInUser.getUserID(),approvedApplicationStatusId,specializedFirm.getCreatedBy());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number :"+paymentUpdateDTO.getAppNo()+" Payment Approved");
        String mailContent = "Dear User,Your application for application number : "+paymentUpdateDTO.getAppNo()+" is approved."+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");

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
        return (String)commonService.getValue("crpspecializedtrade","SPNo","ReferenceNo",appNo);
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
