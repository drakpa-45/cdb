package com.ngn.spring.project.cdb.consultant.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name="crpconsultantworkclassification")
public class ConsultantCategory extends BaseModel implements Serializable{

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpConsultantId")
    private String consultantID;
    @Column(name = "CmnServiceCategoryId")
    private String serviceCateID;
    @Column(name = "CmnAppliedServiceId")
    private String appliedServiceID;
    @Column(name = "CmnVerifiedServiceId")
    private String verifiedServiceID;
    @Column(name = "CmnApprovedServiceId")
    private String approvedServiceID;

    @Transient
    private String appliedServices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsultantID() {
        return consultantID;
    }

    public void setConsultantID(String consultantID) {
        this.consultantID = consultantID;
    }

    public String getServiceCateID() {
        return serviceCateID;
    }

    public void setServiceCateID(String serviceCateID) {
        this.serviceCateID = serviceCateID;
    }

    public String getAppliedServiceID() {
        return appliedServiceID;
    }

    public void setAppliedServiceID(String appliedServiceID) {
        this.appliedServiceID = appliedServiceID;
    }

    public String getVerifiedServiceID() {
        return verifiedServiceID;
    }

    public void setVerifiedServiceID(String verifiedServiceID) {
        this.verifiedServiceID = verifiedServiceID;
    }

    public String getApprovedServiceID() {
        return approvedServiceID;
    }

    public void setApprovedServiceID(String approvedServiceID) {
        this.approvedServiceID = approvedServiceID;
    }

    public String getAppliedServices() {
        return appliedServices;
    }

    public void setAppliedServices(String appliedServices) {
        this.appliedServices = appliedServices;
    }
}
