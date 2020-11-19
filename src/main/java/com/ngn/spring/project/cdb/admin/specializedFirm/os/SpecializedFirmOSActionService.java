package com.ngn.spring.project.cdb.admin.specializedFirm.os;

import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.admin.specializedFirm.SpecializedFirmActionService;
import com.ngn.spring.project.cdb.admin.specializedFirm.renewal.SpecializedFirmRCActionDao;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
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
public class SpecializedFirmOSActionService {

    @Autowired
    private SpecializedFirmRCActionDao specializedFirmRCActionDao;

    @Autowired
    private SpecializedFirmRService specializedFirmRService;
    @Autowired
    private SpecializedFirmActionService nrActionService;
    @Autowired
    private CommonService commonService;
    @Transactional(readOnly =  true)
    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo){
        return specializedFirmRCActionDao.getAppliedServices(applicationNo);
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
     * @return contractor final id
     */
    @Transactional(readOnly = true)
    public String getFinalIdFromCDBNo(String cdbNo){
        return (String)commonService.getValue("crpspecializedtradefinal","Id","SPNo",cdbNo);
    }

    /**
     * To get the CDB no from app no
     * @param appNo  -- cdbNo
     * @return contractor final id
     */
    @Transactional(readOnly = true)
    public String getIdFromAppNo(String appNo){
        return (String)commonService.getValue("crpspecializedtrade","CrpSpecializedTradeId","ReferenceNo",appNo);
    }

}
