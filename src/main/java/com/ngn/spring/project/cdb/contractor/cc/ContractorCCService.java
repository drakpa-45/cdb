package com.ngn.spring.project.cdb.contractor.cc;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAppliedS;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class ContractorCCService extends BaseService {


    @Autowired
    private ContractorRCService contractorRCService;
    @Autowired
    private ContractorCCDao contractorCCDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ContractorNRService contractorNRService;

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

        ContractorFinal contractorFinal = contractorRCService.getContractorFinal(cancellationDTO.getCdbNo());

        Contractor contractor = new Contractor();

        BeanUtils.copyProperties(contractorFinal,contractor);



        String referenceNo = commonService.getNextID("crpcontractor", "ReferenceNo").toString();
        String contractorId = commonService.getRandomGeneratedId();
        contractor.setId(contractorId);
        contractor.setReferenceNo(referenceNo);
        contractor.setdRegBlacklistedRemarks(cancellationDTO.getCancellationReason());

        if(contractor.getContractorId() == null){
            contractor.setContractorId(contractorId);
        }
        contractor.setLockedByUserId(null);
        contractor.setWaiveOffLateFee(BigDecimal.ZERO);
        contractor.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        contractor.setHasNotification("0");
        contractor.setVerifierRemarks(null);
        contractor.setSysVerifierUserId(null);
        contractor.setRegVerifiedDate(null);
        contractor.setApproverRemarks(null);
        contractor.setApproverUserId(null);
        contractor.setRegApprovedDate(null);
        contractor.setFinalApproverUserId(null);
        contractor.setFinalApproverRemarks(null);
        contractor.setPaymentApprovedDate(null);
        contractor.setPaymentReceiptDate(null);
        contractor.setPaymentApproverRemark(null);
        contractor.setPaymentApproverUserId(null);
        contractor.setApplicationDate(loggedInUser.getServerDate());
        contractor.setCreatedBy(loggedInUser.getUserID());
        contractor.setCreatedOn(loggedInUser.getServerDate());
        contractorCCDao.saveOrUpdate(contractor);

        //region save applied service
        ContractorAppliedS contractorAS = new ContractorAppliedS();
        contractorAS.setContractorId(contractorId);
        saveAppliedService(contractorAS,loggedInUser);
        //endregion

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Cancellation request sent successfully.");
        return responseMessage;

    }

    @Transactional(readOnly = false)
    public void saveAppliedService(ContractorAppliedS contractorAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = null;
        if(contractorAppliedS.getServiceTypeId() == null) {
            appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "3");
            contractorAppliedS.setServiceTypeId(appliedServiceId);
        }
        contractorAppliedS.setId(id);
        contractorAppliedS.setCreatedBy(loggedInUser.getUserID());
        contractorAppliedS.setCreatedOn(loggedInUser.getServerDate());

        contractorCCDao.saveUpdate(contractorAppliedS);
    }


}
