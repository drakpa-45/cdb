package com.ngn.spring.project.cdb.specializedFirm.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpspecializedtradeequipment")
public class SpFirmEQ extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpSpecializedTradeId")
    private String specializedTradeId;
    @Column(name = "CmnEquipmentId")
    private String equipmentId;
    @Column(name = "RegistrationNo")
    private String registrationNo;
    @Column(name = "SerialNo")
    private String serialNo;
    @Column(name = "ModelNo")
    private String modelNo;
    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Verified")
    private Integer verified;
    @Column(name = "Approved")
    private Integer approved;

    @Transient
    private Integer deleteRequest;

    @Transient
    private SpFirmEQAttachment spFirmEQA;
    @Transient
    private List<SpFirmEQAttachment> spFirmEQAs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecializedTradeId() {
        return specializedTradeId;
    }

    public void setSpecializedTradeId(String specializedTradeId) {
        this.specializedTradeId = specializedTradeId;
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

    public SpFirmEQAttachment getSpFirmEQA() {
        return spFirmEQA;
    }

    public void setSpFirmEQA(SpFirmEQAttachment spFirmEQA) {
        this.spFirmEQA = spFirmEQA;
    }

    public List<SpFirmEQAttachment> getSpFirmEQAs() {
        return spFirmEQAs;
    }

    public void setSpFirmEQAs(List<SpFirmEQAttachment> spFirmEQAs) {
        this.spFirmEQAs = spFirmEQAs;
    }

    public Integer getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(Integer deleteRequest) {
        this.deleteRequest = deleteRequest;
    }
}
