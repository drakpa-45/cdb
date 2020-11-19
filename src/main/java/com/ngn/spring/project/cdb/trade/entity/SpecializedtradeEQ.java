package com.ngn.spring.project.cdb.trade.entity;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ==================================================================================
 * Created by user on 1/3/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpspecializedtradeequipment")
public class SpecializedtradeEQ extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpSpecializedTradeId")
    private String crpSpecializedTradeId;
    @Column(name = "CmnEquipmentId")
    private String equipmentId;
    @Column(name = "RegistrationNo")
    private String registrationNo;
    @Column(name = "SerialNo")
    private String serialNo;
    @Column(name = "ModelNo")
    private String modelNo;
    @Column(name = "Quantity")
    private String quantity;

    @Column(name = "Verified")
    private Integer verified;
    @Column(name = "Approved")
    private Integer approved;

    private String documentName;
    private String documentPath;
    private String fileType;

    @Transient
    private SpecializedtradeEQAttachment specializedtradeEQAttachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrpSpecializedTradeId() {
        return crpSpecializedTradeId;
    }

    public void setCrpSpecializedTradeId(String crpSpecializedTradeId) {
        this.crpSpecializedTradeId = crpSpecializedTradeId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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

    public SpecializedtradeEQAttachment getSpecializedtradeEQAttachment() {
        return specializedtradeEQAttachment;
    }

    public void setSpecializedtradeEQAttachment(SpecializedtradeEQAttachment specializedtradeEQAttachment) {
        this.specializedtradeEQAttachment = specializedtradeEQAttachment;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
