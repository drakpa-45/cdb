package com.ngn.spring.project.cdb.contractor.registration.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ==================================================================================
 * Created by user on 10/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpcontractorworkclassification")
public class ConCategory extends BaseModel implements Serializable{

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpContractorId")
    private String contractorID;
    @Column(name = "CmnProjectCategoryId")
    private String projectCateID;
    @Column(name = "CmnAppliedClassificationId")
    private String appliedClassID;
    @Column(name = "CmnVerifiedClassificationId")
    private String verifiedClassID;
    @Column(name = "CmnApprovedClassificationId")
    private String approvedClassID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorID() {
        return contractorID;
    }

    public void setContractorID(String contractorID) {
        this.contractorID = contractorID;
    }

    public String getProjectCateID() {
        return projectCateID;
    }

    public void setProjectCateID(String projectCateID) {
        this.projectCateID = projectCateID;
    }

    public String getAppliedClassID() {
        return appliedClassID;
    }

    public void setAppliedClassID(String appliedClassID) {
        this.appliedClassID = appliedClassID;
    }

    public String getVerifiedClassID() {
        return verifiedClassID;
    }

    public void setVerifiedClassID(String verifiedClassID) {
        this.verifiedClassID = verifiedClassID;
    }

    public String getApprovedClassID() {
        return approvedClassID;
    }

    public void setApprovedClassID(String approvedClassID) {
        this.approvedClassID = approvedClassID;
    }
}
