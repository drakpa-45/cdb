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
    //private List<ContractorHRAttachment> contractorHRAs;
    private List<ConsultantCategory> categories;
    private List<ConsultantEQ> equipments;
    private List<ConsultantAttachment> cAttachments;

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
}
