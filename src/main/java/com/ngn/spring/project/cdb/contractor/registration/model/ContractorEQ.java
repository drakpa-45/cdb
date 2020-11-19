package com.ngn.spring.project.cdb.contractor.registration.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 1/3/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpcontractorequipment")
public class ContractorEQ extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpContractorId")
    private String contractorId;
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
    private ContractorEQAttachment contractorEQA;

    @Transient
    private List<ContractorEQAttachment> contractorEQAs;
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

    public ContractorEQAttachment getContractorEQA() {
        return contractorEQA;
    }

    public void setContractorEQA(ContractorEQAttachment contractorEQA) {
        this.contractorEQA = contractorEQA;
    }

    public List<ContractorEQAttachment> getContractorEQAs() {
        return contractorEQAs;
    }

    public void setContractorEQAs(List<ContractorEQAttachment> contractorEQAs) {
        this.contractorEQAs = contractorEQAs;
    }
}
