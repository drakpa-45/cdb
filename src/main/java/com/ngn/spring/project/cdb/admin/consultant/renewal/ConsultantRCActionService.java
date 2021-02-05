package com.ngn.spring.project.cdb.admin.consultant.renewal;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.consultant.registration.ConsultantNRActionService;
import com.ngn.spring.project.cdb.admin.contractor.renewal.ContractorRCActionDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.admin.dto.PaymentUpdateDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.consultant.model.Consultant;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCDao;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.global.global.SmsSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * ====================================================================
 * Created by Pema Drakpa on 8/3/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Service
public class ConsultantRCActionService extends BaseService{

    @Autowired
    private ConsultantRCActionDao consultantRCActionDao;
    @Autowired
    private ConsultantRCService consultantRCService;
    @Autowired
    private ConsultantNRActionService nrActionService;
    @Autowired
    private ConsultantNRService consultantNRService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private ConsultantRCDao consultantRCDao;

    @Transactional(readOnly =  true)
    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo){
        return consultantRCActionDao.getAppliedServices(applicationNo);
    }

    @Transactional(readOnly =  true)
    public Object getConsultantInfoOwner(String applicationNo){
        NewDeleteExistDTO ndeDTOhr = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(applicationNo);
        String consultantFinalId = getFinalIdFromCDBNo(cdbNo);
        List<ConsultantHrDTO> existingHRs = consultantRCService.getConsultantHRsFinal(consultantFinalId, 'O');
        List<ConsultantHrDTO> newHRs = nrActionService.getConsultantHRs(getIdFromAppNo(applicationNo), 'O');
        List<ConsultantHrDTO> editedHRs = newHRs.stream().filter(existingHRs::contains).collect(Collectors.toList());
        List<ConsultantHrDTO> newlyAddedHRs = newHRs.stream().filter(e->!editedHRs.contains(e)).collect(Collectors.toList());
        List<ConsultantHrDTO> deletedHRs = existingHRs.stream().filter(e->e.getDeleteRequest() != null && e.getDeleteRequest() == 1).collect(Collectors.toList());
        ndeDTOhr.setExisting(existingHRs);
        ndeDTOhr.setEdited(editedHRs);
        ndeDTOhr.setNewlyAdded(newlyAddedHRs);
        ndeDTOhr.setDeleted(deletedHRs);
        return ndeDTOhr;
    }

    @Transactional(readOnly =  true)
    public Object getHrsExistingAndNew(String applicationNo){
        NewDeleteExistDTO ndeDTOhr = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(applicationNo);
        String consultantFinalId = getFinalIdFromCDBNo(cdbNo);
        List<ConsultantHrDTO> existingHRs = consultantRCService.getConsultantHRsFinal(consultantFinalId, 'H');
        List<ConsultantHrDTO> newHRs = nrActionService.getConsultantHRs(getIdFromAppNo(applicationNo), 'H');
        List<ConsultantHrDTO> editedHRs = newHRs.stream().filter(existingHRs::contains).collect(Collectors.toList());
        List<ConsultantHrDTO> newlyAddedHRs = newHRs.stream().filter(e->!editedHRs.contains(e)).collect(Collectors.toList());
        List<ConsultantHrDTO> deletedHRs = existingHRs.stream().filter(e->e.getDeleteRequest() != null && e.getDeleteRequest() == 1).collect(Collectors.toList());
        ndeDTOhr.setExisting(existingHRs);
        ndeDTOhr.setEdited(editedHRs);
        ndeDTOhr.setNewlyAdded(newlyAddedHRs);
        ndeDTOhr.setDeleted(deletedHRs);
        return ndeDTOhr;
    }

    /**
     * To get the CDB no from cdb no
     * @param cdbNo  -- cdbNo
     * @return consultant final id
     */
    @Transactional(readOnly = true)
    public String getFinalIdFromCDBNo(String cdbNo){
        return (String)commonService.getValue("crpconsultantfinal","Id","CDBNo",cdbNo);
    }

    /**
     * To get the CDB no from app no
     * @param appNo  -- cdbNo
     * @return contractor final id
     */
    @Transactional(readOnly = true)
    public String getIdFromAppNo(String appNo){
        return (String)commonService.getValue("crpconsultant","CrpConsultantId","ReferenceNo",appNo);
    }

    @Transactional
    public Object getEQs(String appNo) {
        NewDeleteExistDTO ndeDTOeq = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(appNo);
        String contractorFinalId = getFinalIdFromCDBNo(cdbNo);
        List<EquipmentDTO> existingEQs = consultantRCService.getEquipmentFinal(contractorFinalId);
        List<EquipmentDTO> newEQs = nrActionService.getConsultantEQs(getIdFromAppNo(appNo));
        List<EquipmentDTO> editedEQs = newEQs.stream().filter(existingEQs::contains).collect(Collectors.toList());
        List<EquipmentDTO> newlyAddedEQs = newEQs.stream().filter(e->!editedEQs.contains(e)).collect(Collectors.toList());
        List<EquipmentDTO> deletedEQs = existingEQs.stream().filter(e->e.getDeleteRequest() != null && e.getDeleteRequest() == 1).collect(Collectors.toList());
        ndeDTOeq.setExisting(existingEQs);
        ndeDTOeq.setEdited(editedEQs);
        ndeDTOeq.setNewlyAdded(newlyAddedEQs);
        ndeDTOeq.setDeleted(deletedEQs);
        return ndeDTOeq;
    }

    /**
     * To get the services fee of other services if refNo is null or fee of that refNo
     * @param refNo  - reference No
     * @return list
     */
    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        return consultantRCDao.getServicesFee(refNo);
    }

    @Transactional(readOnly = true)
    public List getProposedCategories(String appNo) {
        return consultantRCDao.getProposedCategories(appNo);
    }

    @Transactional(readOnly = false)
    public ResponseMessage paymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser)  throws Exception {
        Consultant consultant = consultantNRService.getConsultant(paymentUpdateDTO.getAppNo());
        String approvedApplicationStatusId = (String) commonService.getValue("cmnlistitem", "Id", "ReferenceNo", "12003");
        // consultant.setAppStatusId(approvedApplicationStatusId);
        consultant.setAppStatusId(ApplicationStatus.APPROVED.getCode());
        consultant.setRegStatus("3");
        consultant.setCdbNo(paymentUpdateDTO.getCdbNo());
        consultant.setPaymentApprovedDate(loggedInUser.getServerDate());
        consultant.setPaymentApproverUserId(loggedInUser.getUserID());
        consultant.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
        //consultant.setLockedByUserId("null");
        consultant.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        consultant.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        consultant.setRegApprovedDate(loggedInUser.getServerDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        consultant.setHasNotification("0");
        calendar.add(Calendar.YEAR, 2);
        consultant.setRegExpiryDate(calendar.getTime());
        consultantRCActionDao.saveOrUpdate(consultant);

        paymentUpdateDTO.setConsultantId(consultant.getConsultantId());
      //  consultantRCActionDao.paymentUpdate(consultant.getConsultantId(), loggedInUser.getUserID(), approvedApplicationStatusId, consultant.getCreatedBy());

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Consultant application number :" + paymentUpdateDTO.getAppNo() + " Payment Approved");
        String mailContent = "Dear User,Your application for application number : " + paymentUpdateDTO.getAppNo() + " is approved." +
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(consultant.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");
        SmsSender.smsSender(consultant.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");
        return responseMessage;
    }




}
