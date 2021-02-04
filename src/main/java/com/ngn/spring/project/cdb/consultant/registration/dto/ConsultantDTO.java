package com.ngn.spring.project.cdb.consultant.registration.dto;

import com.ngn.spring.project.cdb.consultant.model.*;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 1/3/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ConsultantDTO {
    private Consultant consultant;
        private List<ConsultantHR> consultantHRs;
        private List<ConsultantHR> consultantOWs;
    //private List<ContractorHRAttachment> contractorHRAs;
    private List<ConsultantCategory> categories;
    private List<ConsultantEQ> equipments;
    private List<ConsultantAttachment> cAttachments;
    private List<ConsultantAttachment> ownerAttachments;
    private List<ConsultantAttachment> categoryAttachments;

    private ConsultantServicePayment servicePayment;

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public List<ConsultantHR> getConsultantHRs() {
        return consultantHRs;
    }

    public void setConsultantHRs(List<ConsultantHR> consultantHRs) {
        this.consultantHRs = consultantHRs;
    }

    public List<ConsultantHR> getConsultantOWs() {
        return consultantOWs;
    }

    public void setConsultantOWs(List<ConsultantHR> consultantOWs) {
        this.consultantOWs = consultantOWs;
    }

    public List<ConsultantCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ConsultantCategory> categories) {
        this.categories = categories;
    }

    public List<ConsultantEQ> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<ConsultantEQ> equipments) {
        this.equipments = equipments;
    }

    public List<ConsultantAttachment> getcAttachments() {
        return cAttachments;
    }

    public void setcAttachments(List<ConsultantAttachment> cAttachments) {
        this.cAttachments = cAttachments;
    }

    public ConsultantServicePayment getServicePayment() {
        return servicePayment;
    }

    public void setServicePayment(ConsultantServicePayment servicePayment) {
        this.servicePayment = servicePayment;
    }

    public List<ConsultantAttachment> getOwnerAttachments() {
        return ownerAttachments;
    }

    public void setOwnerAttachments(List<ConsultantAttachment> ownerAttachments) {
        this.ownerAttachments = ownerAttachments;
    }

    public List<ConsultantAttachment> getCategoryAttachments() {
        return categoryAttachments;
    }

    public void setCategoryAttachments(List<ConsultantAttachment> categoryAttachments) {
        this.categoryAttachments = categoryAttachments;
    }
}
