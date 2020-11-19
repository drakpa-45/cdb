package com.ngn.spring.project.cdb.consultant.cc;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.model.Consultant;
import com.ngn.spring.project.cdb.consultant.model.ConsultantAppliedS;
import com.ngn.spring.project.cdb.consultant.model.ConsultantFinal;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTOFetch;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
import com.ngn.spring.project.cdb.contractor.cc.CancellationDTO;
import com.ngn.spring.project.cdb.contractor.cc.ContractorCCDao;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAppliedS;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class ConsultantCCService extends BaseService {


    @Autowired
    private ConsultantRCService consultantRCService;
    @Autowired
    private ConsultantCCDao consultantCCDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ConsultantNRService consultantNRService;

    /**
     * Save cancellation of certificate
     * @param cancellationDTO - dto
     * @param loggedInUser  --  logged in user
     * @return
     */
    @Transactional(readOnly = false)
    public ResponseMessage save(CancellationDTO cancellationDTO,LoggedInUser loggedInUser){
        if(cancellationDTO == null){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please fill all mandatory fields.");
            return responseMessage;
        }
        if(cancellationDTO.getCdbNo() == null){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("CDB No empty.");
            return responseMessage;
        }
        if(cancellationDTO.getCancelConfirm() != 'C'){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("You need to check confirmation checkbox to proceed.");
            return responseMessage;
        }

        ConsultantFinal consultantFinal = consultantRCService.getConsultantFinal(cancellationDTO.getCdbNo());

        Consultant consultant = new Consultant();

        BeanUtils.copyProperties(consultantFinal,consultant);



        String referenceNo = commonService.getNextID("crpconsultant", "ReferenceNo").toString();
        String consultantId = commonService.getRandomGeneratedId();
        consultant.setId(consultantId);
        consultant.setReferenceNo(referenceNo);
        consultant.setdRegBlacklistedRemarks(cancellationDTO.getCancellationReason());

        if(consultant.getConsultantId() == null){
            consultant.setConsultantId(consultantId);
        }
        consultant.setLockedByUserId(null);
        consultant.setWaiveOffLateFee(BigDecimal.ZERO);
        consultant.setAppStatusId(ApplicationStatus.VERIFIED.getCode());
        consultant.setHasNotification("0");
        consultant.setVerifierRemarks(null);
        consultant.setSysVerifierUserId(null);
        consultant.setVerifiedDate(null);
        consultant.setApproverRemarks(null);
        consultant.setApproverUserId(null);
        consultant.setRegApprovedDate(null);
        consultant.setFinalApproverUserId(null);
        consultant.setFinalApproverRemarks(null);
        consultant.setPaymentApprovedDate(null);
        consultant.setPaymentReceiptDate(null);
        consultant.setPaymentApproverRemark(null);
        consultant.setPaymentApproverUserId(null);
        consultant.setApplicationDate(loggedInUser.getServerDate());
        consultant.setCreatedBy(loggedInUser.getUserID());
        consultant.setCreatedOn(loggedInUser.getServerDate());
        consultantCCDao.saveOrUpdate(consultant);

        //region save applied service
        ConsultantAppliedS consultantAS = new ConsultantAppliedS();
        consultantAS.setConsultantId(consultantId);
        saveAppliedService(consultantAS,loggedInUser);
        //endregion

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Cancellation request sent successfully.");
        return responseMessage;

    }

    @Transactional(readOnly = false)
    public void saveAppliedService(ConsultantAppliedS consultantAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = null;
        if(consultantAppliedS.getServiceTypeId() == null) {
            appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "3");
            consultantAppliedS.setServiceTypeId(appliedServiceId);
        }
        consultantAppliedS.setId(id);
        consultantAppliedS.setCreatedBy(loggedInUser.getUserID());
        consultantAppliedS.setCreatedOn(loggedInUser.getServerDate());

        consultantCCDao.saveUpdate(consultantAppliedS);
    }

    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo){
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        ConsultantFinal cFinal = consultantRCService.getConsultantFinal(cdbNo);

        //region check for terminated or end of contractor certificate
        List<String> statusList = new ArrayList<>();
        statusList.add(ApplicationStatus.BLACKLISTED.getCode());
        statusList.add(ApplicationStatus.DEBARRED.getCode());
        statusList.add(ApplicationStatus.DEREGISTERED.getCode());
        statusList.add(ApplicationStatus.REJECTED.getCode());
        statusList.add(ApplicationStatus.REVOKED.getCode());
        statusList.add(ApplicationStatus.SUSPENDED.getCode());
        statusList.add(ApplicationStatus.SURRENDERED.getCode());
        statusList.add(ApplicationStatus.CANCELLATION.getCode());
        if(statusList.contains(cFinal.getAppStatusId())){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("You are not allow to avail this service as your certificate is " +
                    "<b> "+ApplicationStatus.valueOf(cFinal.getAppStatusId()).getName()+"</b>");
            return responseMessage;
        }
        //endregion

        String ongoingApp = getOngoingAppStatusMsg(cdbNo);
        if(ongoingApp != null){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText(ongoingApp);
            return responseMessage;
        }
        responseMessage.setText("You are applying for cancellation of specialized Firm CDB certificate.");

        //endregion
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(cFinal);
        responseMessage.setDto1(lateFeeDTO);
        //responseMessage.setValue(String.valueOf(waiveOffLateFee));
        //responseMessage.setVal2(lateFee.toString());
        return responseMessage;
    }

    public String getOngoingAppStatusMsg(String cdbNo){
        ConsultantDTOFetch ongoingApp = consultantNRService.getConsultantOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +". Please wait until complete process for this application.";
    }
}
