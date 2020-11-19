package com.ngn.spring.project.cdb.specializedFirm.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpspecializedtraderegistrationpayment")
public class SpFirmRegPayment extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpSpecializedTradeFinalId")
    private String specializedTradeFinalId;
    @Column(name = "CmnCategoryId")
    private String categoryId;
    @Column(name = "CmnAppliedCategoryId")
    private String appliedCategoryId;
    @Column(name = "CmnVerifiedCategoryId")
    private String verifiedCategoryId;
    @Column(name = "CmnApprovedCategoryId")
    private String approvedCategoryId;
    @Column(name = "ServiceXFee")
    private String serviceXFee;
    @Column(name = "Amount")
    private BigDecimal amount;
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

    public String getSpecializedTradeFinalId() {
        return specializedTradeFinalId;
    }

    public void setSpecializedTradeFinalId(String specializedTradeFinalId) {
        this.specializedTradeFinalId = specializedTradeFinalId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getServiceXFee() {
        return serviceXFee;
    }

    public void setServiceXFee(String serviceXFee) {
        this.serviceXFee = serviceXFee;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAppliedCategoryId() {
        return appliedCategoryId;
    }

    public void setAppliedCategoryId(String appliedCategoryId) {
        this.appliedCategoryId = appliedCategoryId;
    }

    public String getVerifiedCategoryId() {
        return verifiedCategoryId;
    }

    public void setVerifiedCategoryId(String verifiedCategoryId) {
        this.verifiedCategoryId = verifiedCategoryId;
    }

    public String getApprovedCategoryId() {
        return approvedCategoryId;
    }

    public void setApprovedCategoryId(String approvedCategoryId) {
        this.approvedCategoryId = approvedCategoryId;
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
