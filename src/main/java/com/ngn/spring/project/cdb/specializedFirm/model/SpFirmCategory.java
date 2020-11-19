package com.ngn.spring.project.cdb.specializedFirm.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name="crpspecializedtradeworkclassification")
public class SpFirmCategory extends BaseModel implements Serializable{
    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpSpecializedTradeId")
    private String specializedID;
    @Column(name = "CmnAppliedCategoryId")
    private String appliedCategoryId;
    @Column(name = "CmnVerifiedCategoryId")
    private String verifiedCategoryId;
    @Column(name = "CmnApprovedCategoryId")
    private String approvedCategoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecializedID() {
        return specializedID;
    }

    public void setSpecializedID(String specializedID) {
        this.specializedID = specializedID;
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
}
