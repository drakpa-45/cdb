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
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpconsultantregistrationpayment")
public class ConsultantRegPayment  extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpConsultantFinalId")
    private String consultantId;
    @Column(name = "CmnServiceCategoryId")
    private String categoryId;
    @Column(name = "AppliedService")
    private String appliedServices;
    @Column(name = "VerifiedService")
    private String verifiedServiceId;
    @Column(name = "ApprovedService")
    private String approvedServiceId;
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

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAppliedServices() {
        return appliedServices;
    }

    public void setAppliedServices(String appliedServices) {
        this.appliedServices = appliedServices;
    }

    public String getVerifiedServiceId() {
        return verifiedServiceId;
    }

    public void setVerifiedServiceId(String verifiedServiceId) {
        this.verifiedServiceId = verifiedServiceId;
    }

    public String getApprovedServiceId() {
        return approvedServiceId;
    }

    public void setApprovedServiceId(String approvedServiceId) {
        this.approvedServiceId = approvedServiceId;
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
