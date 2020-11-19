package com.ngn.spring.project.cdb.admin.dto;

import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmAttachment;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirm;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/19/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class SpecializedFirmInfoDTO {
    private SpecializedFirm specializedFirm;
    private List<SpFirmHrDTO> spFirmHRs;
    private List<SpFirmAttachment> spFirmHRAs;
    private List<CategoryClassDTO> categories;
    private List<EquipmentDTO> equipments;
    private List<ApplicationHistoryDTO> appHistoryDTOs;
    private List<SpFirmAttachment> incAttachments;

    private String ownershipTypeTxt;
    private String countryTxt;
    private String pDzongkhagTxt;
    private String pGewogTxt;
    private String pVillageTxt;
    private String estDzongkhagTxt;

    private String cdbNo;

    public SpecializedFirm getSpecializedFirm() {
        return specializedFirm;
    }

    public void setSpecializedFirm(SpecializedFirm specializedFirm) {
        this.specializedFirm = specializedFirm;
    }

    public List<SpFirmHrDTO> getSpFirmHRs() {
        return spFirmHRs;
    }

    public void setSpFirmHRs(List<SpFirmHrDTO> spFirmHRs) {
        this.spFirmHRs = spFirmHRs;
    }

    public List<SpFirmAttachment> getSpFirmHRAs() {
        return spFirmHRAs;
    }

    public void setSpFirmHRAs(List<SpFirmAttachment> spFirmHRAs) {
        this.spFirmHRAs = spFirmHRAs;
    }

    public List<CategoryClassDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryClassDTO> categories) {
        this.categories = categories;
    }

    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }

    public List<ApplicationHistoryDTO> getAppHistoryDTOs() {
        return appHistoryDTOs;
    }

    public void setAppHistoryDTOs(List<ApplicationHistoryDTO> appHistoryDTOs) {
        this.appHistoryDTOs = appHistoryDTOs;
    }

    public String getOwnershipTypeTxt() {
        return ownershipTypeTxt;
    }

    public void setOwnershipTypeTxt(String ownershipTypeTxt) {
        this.ownershipTypeTxt = ownershipTypeTxt;
    }

    public String getCountryTxt() {
        return countryTxt;
    }

    public void setCountryTxt(String countryTxt) {
        this.countryTxt = countryTxt;
    }

    public String getpDzongkhagTxt() {
        return pDzongkhagTxt;
    }

    public void setpDzongkhagTxt(String pDzongkhagTxt) {
        this.pDzongkhagTxt = pDzongkhagTxt;
    }

    public String getpGewogTxt() {
        return pGewogTxt;
    }

    public void setpGewogTxt(String pGewogTxt) {
        this.pGewogTxt = pGewogTxt;
    }

    public String getpVillageTxt() {
        return pVillageTxt;
    }

    public void setpVillageTxt(String pVillageTxt) {
        this.pVillageTxt = pVillageTxt;
    }

    public String getEstDzongkhagTxt() {
        return estDzongkhagTxt;
    }

    public void setEstDzongkhagTxt(String estDzongkhagTxt) {
        this.estDzongkhagTxt = estDzongkhagTxt;
    }

    public List<SpFirmAttachment> getIncAttachments() {
        return incAttachments;
    }

    public void setIncAttachments(List<SpFirmAttachment> incAttachments) {
        this.incAttachments = incAttachments;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }
}
