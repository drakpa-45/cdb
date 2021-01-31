package com.ngn.spring.project.cdb.contractor.registration.dto;

import com.ngn.spring.project.cdb.contractor.registration.model.*;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 1/3/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ContractorDTO {
    private Contractor contractor;
    private List<ContractorHR> contractorHRs;
    //private List<ContractorHRAttachment> contractorHRAs;
    private List<ConCategory> categories;
    private List<ContractorEQ> equipments;
    private List<ContractorAttachment> cAttachments;
    private List<ContractorAttachment> ownerAttachments;
    private List<ContractorAttachment> categoryAttachments;


    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<ContractorHR> getContractorHRs() {
        return contractorHRs;
    }

    public void setContractorHRs(List<ContractorHR> contractorHRs) {
        this.contractorHRs = contractorHRs;
    }

    public List<ConCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ConCategory> categories) {
        this.categories = categories;
    }

    public List<ContractorEQ> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<ContractorEQ> equipments) {
        this.equipments = equipments;
    }

    public List<ContractorAttachment> getcAttachments() {
        return cAttachments;
    }

    public void setcAttachments(List<ContractorAttachment> cAttachments) {
        this.cAttachments = cAttachments;
    }

    public List<ContractorAttachment> getOwnerAttachments() {
        return ownerAttachments;
    }

    public void setOwnerAttachments(List<ContractorAttachment> ownerAttachments) {
        this.ownerAttachments = ownerAttachments;
    }

    public List<ContractorAttachment> getCategoryAttachments() {
        return categoryAttachments;
    }

    public void setCategoryAttachments(List<ContractorAttachment> categoryAttachments) {
        this.categoryAttachments = categoryAttachments;
    }
}
