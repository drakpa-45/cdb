package com.ngn.spring.project.cdb.admin.contractor.renewal;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.admin.dto.PaymentUpdateDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/3/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Service
public class ContractorRCActionService extends BaseService {

    @Autowired
    private ContractorRCActionDao contractorRCActionDao;

    @Autowired
    private ContractorRCService contractorRCService;
    @Autowired
    private ContractorNRActionService nrActionService;
    @Autowired
    private ContractorNRService contractorNRService;
    @Autowired
    private CommonService commonService;
    @Transactional(readOnly =  true)
    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo){
        return contractorRCActionDao.getAppliedServices(applicationNo);
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
        contractorRCActionDao.saveOrUpdate(contractor);

        paymentUpdateDTO.setContractorId(contractor.getContractorId());
        contractorRCActionDao.paymentUpdate(contractor.getContractorId(),loggedInUser.getUserID(),approvedApplicationStatusId,contractor.getCreatedBy());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+paymentUpdateDTO.getAppNo()+" Payment Approved");
        String mailContent = "Dear User,Your application for application number : "+paymentUpdateDTO.getAppNo()+" is approved."+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(contractor.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");

        return responseMessage;
    }

    @Transactional(readOnly =  true)
    public Object getHrsExistingAndNew(String applicationNo){
        NewDeleteExistDTO ndeDTOhr = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(applicationNo);
        String contractorFinalId = getFinalIdFromCDBNo(cdbNo);
        List<ContractorHrDTO> existingHRs = contractorRCService.getContractorHRsFinal(contractorFinalId, 'H');
        List<ContractorHrDTO> newHRs = nrActionService.getContractorHRs(getIdFromAppNo(applicationNo), 'H');
        List<ContractorHrDTO> editedHRs = newHRs.stream().filter(existingHRs::contains).collect(Collectors.toList());
        List<ContractorHrDTO> newlyAddedHRs = newHRs.stream().filter(e->!editedHRs.contains(e)).collect(Collectors.toList());
        List<ContractorHrDTO> deletedHRs = existingHRs.stream().filter(e->e.getDeleteRequest() != null && e.getDeleteRequest() == 1).collect(Collectors.toList());
        ndeDTOhr.setExisting(existingHRs);
       ndeDTOhr.setEdited(editedHRs);
        ndeDTOhr.setNewlyAdded(newlyAddedHRs);
        ndeDTOhr.setDeleted(deletedHRs);
        return ndeDTOhr;
    }

    @Transactional
    public Object getEQs(String appNo) {
        NewDeleteExistDTO ndeDTOeq = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(appNo);
        String contractorFinalId = getFinalIdFromCDBNo(cdbNo);
        List<EquipmentDTO> existingEQs = contractorRCService.getEquipmentFinal(contractorFinalId);
        List<EquipmentDTO> newEQs = nrActionService.getContractorEQs(getIdFromAppNo(appNo));
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
     * To get the CDB no from cdb no
     * @param cdbNo  -- cdbNo
     * @return contractor final id
     */
    @Transactional(readOnly = true)
    public String getFinalIdFromCDBNo(String cdbNo){
        return (String)commonService.getValue("crpcontractorfinal","Id","CDBNo",cdbNo);
    }

    /**
     * To get the CrpContractorId no from app no
     * @param appNo  -- appNo
     * @return contractor CrpContractorId
     */
    @Transactional(readOnly = true)
    public String getIdFromAppNo(String appNo){
        return (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo);
    }

    @Transactional(readOnly = true)
    public List getProposedCategories(String appNo) {
        return contractorRCActionDao.getProposedCategories(appNo);
    }
}
