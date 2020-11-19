package com.ngn.spring.project.cdb.admin.consultant.os;

import com.ngn.spring.project.cdb.admin.consultant.registration.ConsultantNRActionService;
import com.ngn.spring.project.cdb.admin.consultant.renewal.ConsultantRCActionDao;
import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
import com.ngn.spring.project.cdb.admin.contractor.renewal.ContractorRCActionDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
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
public class ConsultantOSActionService {

    @Autowired
    private ConsultantRCActionDao consultantRCActionDao;

    @Autowired
    private ConsultantRCService consultantRCService;
    @Autowired
    private ConsultantNRActionService nrActionService;
    @Autowired
    private CommonService commonService;
    @Transactional(readOnly =  true)
    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo){
        return consultantRCActionDao.getAppliedServices(applicationNo);
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
