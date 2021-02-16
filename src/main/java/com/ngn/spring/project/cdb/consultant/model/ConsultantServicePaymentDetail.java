package com.ngn.spring.project.cdb.consultant.model;

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
@Table(name = "crpconsultantservicepaymentdetail")
public class ConsultantServicePaymentDetail extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpConsultantServicePaymentId")
    private String servicePaymentId;
    @Column(name = "CmnServiceCategoryId")
    private String categoryId;
   // @Column(name = "CmnExistingClassificationId")
    private String existingClassId;
    @Column(name = "AppliedService")
    private String appliedClassId;
    @Column(name = "VerifiedService")
    private String verifiedClassId;
    @Column(name = "ApprovedService")
    private String approvedClassId;
    @Column(name = "ServiceXFee")
    private String serviceXFee;
    @Column(name = "Amount")
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServicePaymentId() {
        return servicePaymentId;
    }

    public void setServicePaymentId(String servicePaymentId) {
        this.servicePaymentId = servicePaymentId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getExistingClassId() {
        return existingClassId;
    }

    public void setExistingClassId(String existingClassId) {
        this.existingClassId = existingClassId;
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
}
