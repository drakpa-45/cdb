package com.ngn.spring.project.cdb.admin.dto;

import com.ngn.spring.project.cdb.consultant.model.ConsultantAttachment;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import com.ngn.spring.project.cdb.consultant.model.Consultant;
import com.ngn.spring.project.cdb.consultant.model.ConsultantHRAttachment;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/19/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ConsultantInfoDTO {
    private Consultant consultant;
    private List<ConsultantHrDTO> consultantHRs;
    private List<ConsultantHRAttachment> consultantHRAs;
    private List<CategoryClassDTO> categories;
    private List<EquipmentDTO> equipments;
    private List<ConsultantAttachment> incAttachments;
    private List<ApplicationHistoryDTO> appHistoryDTOs;

    private String ownershipTypeTxt;
    private String countryTxt;
    private String pDzongkhagTxt;
    private String pGewogTxt;
    private String pVillageTxt;
    private String estDzongkhagTxt;

    private String cdbNo;

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public List<ConsultantHrDTO> getConsultantHRs() {
        return consultantHRs;
    }

    public void setConsultantHRs(List<ConsultantHrDTO> consultantHRs) {
        this.consultantHRs = consultantHRs;
    }

    public List<ConsultantHRAttachment> getConsultantHRAs() {
        return consultantHRAs;
    }

    public void setConsultantHRAs(List<ConsultantHRAttachment> consultantHRAs) {
        this.consultantHRAs = consultantHRAs;
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

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public List<ConsultantAttachment> getIncAttachments() {
        return incAttachments;
    }

    public void setIncAttachments(List<ConsultantAttachment> incAttachments) {
        this.incAttachments = incAttachments;
    }
}
