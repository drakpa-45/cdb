package com.ngn.spring.project.cdb.admin.dto;

import com.ngn.spring.project.cdb.common.dto.VehicleDetails;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/20/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class EquipmentDTO {

    private String id;
    private String contractorId;
    private String consultantId;
    private String specializedFirmId;
    private String equipmentId;
    private String registrationNo;
    private String serialNo;
    private String modelNo;
    private Integer quantity;

    private Integer verified;
    private Integer approved;
    private Integer deleteRequest;
    private Integer editCheck;

    private String equipmentName;


    List<AttachmentDTO> eqAttachments;

    List<VehicleDetails> vehicleDetailses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public List<AttachmentDTO> getEqAttachments() {
        return eqAttachments;
    }

    public Integer getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(Integer deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

    public Integer getEditCheck() {
        return editCheck;
    }

    public void setEditCheck(Integer editCheck) {
        this.editCheck = editCheck;
    }

    public void setEqAttachments(List<AttachmentDTO> eqAttachments) {
        this.eqAttachments = eqAttachments;
    }

    public String getSpecializedFirmId() {
        return specializedFirmId;
    }

    public void setSpecializedFirmId(String specializedFirmId) {
        this.specializedFirmId = specializedFirmId;
    }

    public List<VehicleDetails> getVehicleDetailses() {
        return vehicleDetailses;
    }

    public void setVehicleDetailses(List<VehicleDetails> vehicleDetailses) {
        this.vehicleDetailses = vehicleDetailses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentDTO that = (EquipmentDTO) o;

        if (!registrationNo.equals(that.registrationNo)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return registrationNo.hashCode();
    }
}
