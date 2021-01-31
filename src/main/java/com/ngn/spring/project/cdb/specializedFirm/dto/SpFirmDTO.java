package com.ngn.spring.project.cdb.specializedFirm.dto;

import com.ngn.spring.project.cdb.specializedFirm.model.*;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 1/3/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class SpFirmDTO {
    private SpecializedFirm specializedFirm;
    private List<SpFirmHR> spFirmHRs;
    private List<SpFirmCategory> categories;
    private List<SpFirmEQ> equipments;
    private List<SpFirmAttachment> cAttachments;
    private List<SpFirmAttachment> ownerAttachments;
    private List<SpFirmAttachment> categoryAttachments;

    public SpecializedFirm getSpecializedFirm() {
        return specializedFirm;
    }

    public void setSpecializedFirm(SpecializedFirm specializedFirm) {
        this.specializedFirm = specializedFirm;
    }

    public List<SpFirmHR> getSpFirmHRs() {
        return spFirmHRs;
    }

    public void setSpFirmHRs(List<SpFirmHR> spFirmHRs) {
        this.spFirmHRs = spFirmHRs;
    }

    public List<SpFirmCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SpFirmCategory> categories) {
        this.categories = categories;
    }

    public List<SpFirmEQ> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<SpFirmEQ> equipments) {
        this.equipments = equipments;
    }

    public List<SpFirmAttachment> getcAttachments() {
        return cAttachments;
    }

    public void setcAttachments(List<SpFirmAttachment> cAttachments) {
        this.cAttachments = cAttachments;
    }

    public List<SpFirmAttachment> getOwnerAttachments() {
        return ownerAttachments;
    }

    public void setOwnerAttachments(List<SpFirmAttachment> ownerAttachments) {
        this.ownerAttachments = ownerAttachments;
    }

    public List<SpFirmAttachment> getCategoryAttachments() {
        return categoryAttachments;
    }

    public void setCategoryAttachments(List<SpFirmAttachment> categoryAttachments) {
        this.categoryAttachments = categoryAttachments;
    }
}
