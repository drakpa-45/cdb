package com.ngn.spring.project.cdb.admin.contractor.os;

import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
import com.ngn.spring.project.cdb.admin.contractor.renewal.ContractorRCActionDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ContractorOSActionService {

    @Autowired
    private ContractorRCActionDao contractorRCActionDao;

    @Autowired
    private ContractorRCService contractorRCService;
    @Autowired
    private ContractorNRActionService nrActionService;
    @Autowired
    private CommonService commonService;
    @Transactional(readOnly =  true)
    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo){
        return contractorRCActionDao.getAppliedServices(applicationNo);
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
     * To get the CDB no from app no
     * @param appNo  -- cdbNo
     * @return contractor final id
     */
    @Transactional(readOnly = true)
    public String getIdFromAppNo(String appNo){
        return (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo);
    }

}
