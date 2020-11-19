package com.ngn.spring.project.cdb.specializedFirm.cc;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.cc.CancellationDTO;
import com.ngn.spring.project.cdb.contractor.cc.ContractorCCDao;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAppliedS;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTOFetch;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmAppliedS;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirm;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
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
public class SpecializedFirmCCService extends BaseService {


    @Autowired
    private SpecializedFirmRService specializedFirmRService;
    @Autowired
    private SpecializedFirmCCDao specializedFirmCCDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SpecializedFirmService specializedFirmService;

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

        SpecializedFirmFinal specializedFirmFinal = specializedFirmRService.getSpecializedFirmFinal(cancellationDTO.getCdbNo());

        SpecializedFirm specializedFirm = new SpecializedFirm();

        BeanUtils.copyProperties(specializedFirmFinal,specializedFirm);

        String referenceNo = commonService.getNextID("crpspecializedtrade", "ReferenceNo").toString();
        String specializedFirmId = commonService.getRandomGeneratedId();
        specializedFirm.setId(specializedFirmId);
        specializedFirm.setReferenceNo(referenceNo);
        specializedFirm.setdRegBlacklistedRemarks(cancellationDTO.getCancellationReason());

        if(specializedFirm.getCrpSpecializedTradeId() == null){
            specializedFirm.setCrpSpecializedTradeId(specializedFirmId);
        }
        specializedFirm.setLockedByUserId(null);
        specializedFirm.setWaiveOffLateFee(BigDecimal.ZERO);
        specializedFirm.setAppStatusId(ApplicationStatus.VERIFIED.getCode());
        specializedFirm.setHasNotification("0");
        specializedFirm.setVerifierRemarks(null);
        specializedFirm.setSysVerifierUserId(null);
        specializedFirm.setVerifiedDate(null);
        specializedFirm.setApproverRemarks(null);
        specializedFirm.setApproverUserId(null);
        specializedFirm.setRegApprovedDate(null);
        specializedFirm.setFinalApproverUserId(null);
        specializedFirm.setFinalApproverRemarks(null);
        specializedFirm.setPaymentApprovedDate(null);
        specializedFirm.setPaymentReceiptDate(null);
        specializedFirm.setPaymentApproverRemark(null);
        specializedFirm.setPaymentApproverUserId(null);
        specializedFirm.setApplicationDate(loggedInUser.getServerDate());
        specializedFirm.setCreatedBy(loggedInUser.getUserID());
        specializedFirm.setCreatedOn(loggedInUser.getServerDate());
        specializedFirmCCDao.saveOrUpdate(specializedFirm);

        //region save applied service
        SpFirmAppliedS spFirmAppliedS = new SpFirmAppliedS();
        spFirmAppliedS.setSpecializedTradeId(specializedFirmId);
        saveAppliedService(spFirmAppliedS,loggedInUser);
        //endregion

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Cancellation request sent successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public void saveAppliedService(SpFirmAppliedS spFirmAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = null;
        if(spFirmAppliedS.getServiceTypeId() == null) {
            appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "3");
            spFirmAppliedS.setServiceTypeId(appliedServiceId);
        }
        spFirmAppliedS.setId(id);
        spFirmAppliedS.setCreatedBy(loggedInUser.getUserID());
        spFirmAppliedS.setCreatedOn(loggedInUser.getServerDate());
        specializedFirmCCDao.saveUpdate(spFirmAppliedS);
    }

    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo){
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        SpecializedFirmFinal cFinal = specializedFirmRService.getSpecializedFirmFinal(cdbNo);

        //region check for terminated or end of specializedFirm certificate
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
        SpFirmDTOFetch ongoingApp = specializedFirmService.getSpecializedFirmOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +". Please wait until complete process for this application.";
    }
}
