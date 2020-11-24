package com.ngn.spring.project.cdb.profile;

import com.ngn.spring.project.cdb.admin.contractor.registration.ContractorNRActionService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.model.ConsultantFinal;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ====================================================================
 * Created by Nima Yoezer on 7/18/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Service
public class ProfileService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ContractorRCService contractorRCService;
    @Autowired
    private ConsultantRCService consultantRCService;
    @Autowired
    SpecializedFirmRService specializedFirmRService;

    @Transactional(readOnly = true)
    public Object getApplicationDetails(String cdbNo){
        ContractorFinal contractor = contractorRCService.getContractorFinal(cdbNo);
        contractor.setRegDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractor.getRegDzongkhagId()).toString());
        contractor.setOwnershipName(commonService.getValue("cmnlistitem", "Name", "Id", contractor.getOwnershipTypeId()).toString());
        contractor.setCountryName(commonService.getValue("cmncountry", "Name", "Id", contractor.getpCountryId()).toString());
        return contractor;
    }

    @Transactional(readOnly = true)
    public Object getApplicationDetailsConsultant(String cdbNo) {
        ConsultantFinal consultant = consultantRCService.getConsultantFinal(cdbNo);
        consultant.setDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultant.getpDzongkhagId()).toString());
        consultant.setRegDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultant.getRegDzongkhagId()).toString());
        consultant.setOwnershipName(commonService.getValue("cmnlistitem", "Name", "Id", consultant.getOwnershipTypeId()).toString());
        consultant.setCountryName(commonService.getValue("cmncountry", "Name", "Id", consultant.getpCountryId()).toString());
        return consultant;

    }

    @Transactional(readOnly = true)
    public Object getApplicationDetailsSpecializedFirm(String cdbNo) {
        SpecializedFirmFinal specializedFirm = specializedFirmRService.getSpecializedFirmFinal(cdbNo);
        specializedFirm.setDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirm.getpDzongkhagId()).toString());
        specializedFirm.setRegDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirm.getRegDzongkhagId()).toString());
        specializedFirm.setOwnershipName(commonService.getValue("cmnlistitem", "Name", "Id", specializedFirm.getOwnershipTypeId()).toString());
        specializedFirm.setCountryName(commonService.getValue("cmncountry", "Name", "Id", specializedFirm.getpCountryId()).toString());

        return specializedFirm;
    }
}
