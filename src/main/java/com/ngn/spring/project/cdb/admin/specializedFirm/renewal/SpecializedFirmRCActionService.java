package com.ngn.spring.project.cdb.admin.specializedFirm.renewal;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.admin.dto.PaymentUpdateDTO;
import com.ngn.spring.project.cdb.admin.specializedFirm.SpecializedFirmActionService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirm;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
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
public class SpecializedFirmRCActionService extends BaseService {

    @Autowired
    private SpecializedFirmRCActionDao specializedFirmRCActionDao;

    @Autowired
    private SpecializedFirmRService specializedFirmRService;
    @Autowired
    private SpecializedFirmActionService nrActionService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private SpecializedFirmService specializedFirmService;

    @Transactional(readOnly =  true)
    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo){
        return specializedFirmRCActionDao.getAppliedServices(applicationNo);
    }

    @Transactional(readOnly = false)
    public ResponseMessage paymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser) throws Exception{
        SpecializedFirm specializedFirm = specializedFirmService.getSpecializedFirm(paymentUpdateDTO.getAppNo());
        String approvedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12003");
        // specializedFirm.setAppStatusId(approvedApplicationStatusId);
        specializedFirm.setAppStatusId(ApplicationStatus.APPROVED.getCode());
        specializedFirm.setRegStatus("3");
        specializedFirm.setCdbNo(paymentUpdateDTO.getCdbNo());
        specializedFirm.setPaymentApprovedDate(loggedInUser.getServerDate());
        specializedFirm.setPaymentApproverUserId(loggedInUser.getUserID());
        specializedFirm.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
        //specializedFirm.setLockedByUserId("null");
        specializedFirm.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        specializedFirm.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        specializedFirm.setRegApprovedDate(loggedInUser.getServerDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        specializedFirm.setHasNotification("0");
        calendar.add(Calendar.YEAR, 2);
        specializedFirm.setRegExpiryDate(calendar.getTime());
        specializedFirmRCActionDao.saveOrUpdate(specializedFirm);

        paymentUpdateDTO.setSpecializedFirmId(specializedFirm.getCrpSpecializedTradeId());
        specializedFirmRCActionDao.paymentUpdate(specializedFirm.getCrpSpecializedTradeId(),loggedInUser.getUserID(),approvedApplicationStatusId,specializedFirm.getCreatedBy());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Specialized Firm application number :"+paymentUpdateDTO.getAppNo()+" Payment Approved");
        String mailContent = "Dear User,Your application for application number : "+paymentUpdateDTO.getAppNo()+" is approved."+
                "You can login to the system for renewal other services using following credential:" +
                "Username : your registered email" +
                "Password : 123" +
                "Please change your default password after login.";
        MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application approved");

        return responseMessage;
    }

    @Transactional(readOnly =  true)
    public Object getHrsExistingAndNew(String applicationNo){
        NewDeleteExistDTO ndeDTOhr = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(applicationNo);
        String specializedFirmFinalId = getFinalIdFromCDBNo(cdbNo);
        List<SpFirmHrDTO> existingHRs = specializedFirmRService.getSpecializedFirmHRsFinal(specializedFirmFinalId, 'H');
        List<SpFirmHrDTO> newHRs = nrActionService.getSpecializedFirmHRs(getIdFromAppNo(applicationNo), 'H');
        List<SpFirmHrDTO> editedHRs = newHRs.stream().filter(existingHRs::contains).collect(Collectors.toList());
        List<SpFirmHrDTO> newlyAddedHRs = newHRs.stream().filter(e->!editedHRs.contains(e)).collect(Collectors.toList());
        List<SpFirmHrDTO> deletedHRs = existingHRs.stream().filter(e->e.getDeleteRequest() != null && e.getDeleteRequest() == 1).collect(Collectors.toList());
        ndeDTOhr.setExisting(existingHRs);
        ndeDTOhr.setEdited(editedHRs);
        ndeDTOhr.setNewlyAdded(newlyAddedHRs);
        ndeDTOhr.setDeleted(deletedHRs);
        return ndeDTOhr;
    }

    /**
     * To get the CDB no from cdb no
     * @param cdbNo  -- cdbNo
     * @return specializedFirm final id
     */

    @Transactional(readOnly = true)
    public String getFinalIdFromCDBNo(String cdbNo){
        return (String)commonService.getValue("crpspecializedtradefinal","Id","SPNo",cdbNo);
    }

    /**
     * To get the CDB no from app no
     * @param appNo  -- cdbNo
     * @return specializedFirm final id
     */

    @Transactional(readOnly = true)
    public String getIdFromAppNo(String appNo){
        return (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo);
    }

    @Transactional(readOnly = true)
    public List getProposedCategories(String appNo) {
        return specializedFirmRCActionDao.getProposedCategories(appNo);
    }

    @Transactional
    public Object getEQs(String appNo) {
        NewDeleteExistDTO ndeDTOeq = new NewDeleteExistDTO();
        String cdbNo = nrActionService.getCDBNoFromAppNo(appNo);
        String specializedFirmFinalId = getFinalIdFromCDBNo(cdbNo);
        List<EquipmentDTO> existingEQs = specializedFirmRService.getEquipmentFinal(specializedFirmFinalId);
        List<EquipmentDTO> newEQs = nrActionService.getSpecializedFirmEQs(getIdFromAppNo(appNo));
        List<EquipmentDTO> editedEQs = newEQs.stream().filter(existingEQs::contains).collect(Collectors.toList());
        List<EquipmentDTO> newlyAddedEQs = newEQs.stream().filter(e->!editedEQs.contains(e)).collect(Collectors.toList());
        List<EquipmentDTO> deletedEQs = existingEQs.stream().filter(e->e.getDeleteRequest() != null && e.getDeleteRequest() == 1).collect(Collectors.toList());
        ndeDTOeq.setExisting(existingEQs);
        ndeDTOeq.setEdited(editedEQs);
        ndeDTOeq.setNewlyAdded(newlyAddedEQs);
        ndeDTOeq.setDeleted(deletedEQs);
        return ndeDTOeq;
    }
}
