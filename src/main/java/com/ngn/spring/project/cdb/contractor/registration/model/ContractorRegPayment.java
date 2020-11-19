package com.ngn.spring.project.cdb.contractor.registration.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ==================================================================================
 * Created by user on 3/13/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpcontractorregistrationpayment")
public class ContractorRegPayment extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpContractorFinalId")
    private String contractorId;
    @Column(name = "CmnCategoryId")
    private String categoryId;
    @Column(name = "CmnAppliedClassificationId")
    private String appliedClassId;
    @Column(name = "CmnVerifiedClassificationId")
    private String verifiedClassId;
    @Column(name = "CmnApprovedClassificationId")
    private String approvedClassId;
    @Column(name = "AppliedAmount")
    private BigDecimal appliedAmount;
    @Column(name = "VerifiedAmount")
    private BigDecimal verifiedAmount;
    @Column(name = "ApprovedAmount")
    private BigDecimal approvedAmount;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAppliedClassId() {
        return appliedClassId;
    }

    public void setAppliedClassId(String appliedClassId) {
        this.appliedClassId = appliedClassId;
    }

    public String getVerifiedClassId() {
        return verifiedClassId;
    }

    public void setVerifiedClassId(String verifiedClassId) {
        this.verifiedClassId = verifiedClassId;
    }

    public String getApprovedClassId() {
        return approvedClassId;
    }

    public void setApprovedClassId(String approvedClassId) {
        this.approvedClassId = approvedClassId;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public BigDecimal getVerifiedAmount() {
        return verifiedAmount;
    }

    public void setVerifiedAmount(BigDecimal verifiedAmount) {
        this.verifiedAmount = verifiedAmount;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }
}
