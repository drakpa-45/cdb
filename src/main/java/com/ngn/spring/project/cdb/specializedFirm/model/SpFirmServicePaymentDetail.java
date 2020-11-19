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
 * ==================================================================================
 * Created by user on 3/13/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpspecializedtradeservicepaymentdetail")
public class SpFirmServicePaymentDetail extends BaseModel implements Serializable {
    /*
    SELECT 	`Id`,
	`CrpSpecializedFirmServicePaymentId`,
	`CmnCategoryId`,
	`CmnExistingClassificationId`,
	`CmnAppliedClassificationId`,
	`CmnVerifiedClassificationId`,
	`CmnApprovedClassificationId`,
	`Amount`,
	`CreatedBy`,
	`EditedBy`,
	`CreatedOn`,
	`EditedOn`
    * */

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpSpecializedFirmServicePaymentId")
    private String servicePaymentId;
    @Column(name = "CmnCategoryId")
    private String categoryId;
    @Column(name = "CmnExistingClassificationId")
    private String existingClassId;
    @Column(name = "CmnAppliedClassificationId")
    private String appliedClassId;
    @Column(name = "CmnVerifiedClassificationId")
    private String verifiedClassId;
    @Column(name = "CmnApprovedClassificationId")
    private String approvedClassId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
