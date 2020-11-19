package com.ngn.spring.project.cdb.survey.entity;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by USER on 3/23/2020.
 */

@Entity
@Table(name = "crpsurveyfinal")
public class CrpsurveyFinalEntity implements Serializable{
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "SysUserId")
    private String sysUserId;
    @Column(name = "ReferenceNo")
    private BigInteger referenceNo;
    @Column(name = "InitialDate")
    private Date initialDate;
    @Column(name = "ApplicationDate")
    private Date applicationDate;
    @Column(name = "ARNo")
    private String arNo;
    @Column(name = "CmnServiceSectorTypeId")
    private String cmnServiceSectorTypeId;
    @Column(name = "CIDNo")
    private String cidNo;
    @Column(name = "CmnSalutationId")
    private String cmnSalutationId;
    @Column(name = "Name")
    private String name;
    @Column(name = "CmnCountryId")
    private String cmnCountryId;
    @Column(name = "CmnDzongkhagId")
    private String cmnDzongkhagId;
    @Column(name = "Gewog")
    private String gewog;
    @Column(name = "Village")
    private String village;
    @Column(name = "Email")
    private String email;
    @Column(name = "MobileNo")
    private String mobileNo;
    @Column(name = "EmployerName")
    private String employerName;
    @Column(name = "EmployerAddress")
    private String employerAddress;
    @Column(name = "TPN")
    private String tpn;
    @Column(name = "CmnQualificationId")
    private String cmnQualificationId;
    @Column(name = "GraduationYear")
    private String graduationYear;
    @Column(name = "NameOfUniversity")
    private String nameOfUniversity;
    @Column(name = "CmnUniversityCountryId")
    private String cmnUniversityCountryId;
    @Column(name = "CmnApplicationRegistrationStatusId")
    private String cmnApplicationRegistrationStatusId;
    @Column(name = "BlacklistedRemarks")
    private String blacklistedRemarks;
    @Column(name = "DeregisteredRemarks")
    private String deregisteredRemarks;
    @Column(name = "DeRegisteredDate")
    private Date deRegisteredDate;
    @Column(name = "BlacklistedDate")
    private Date blacklistedDate;
    @Column(name = "ReRegistrationDate")
    private Date reRegistrationDate;
    @Column(name = "ReRegistrationRemarks")
    private String reRegistrationRemarks;
    @Column(name = "RegistrationApprovedDate")
    private Date registrationApprovedDate;
    @Column(name = "RegistrationExpiryDate")
    private Date registrationExpiryDate;
    @Column(name = "SysFinalApproverUserId")
    private String sysFinalApproverUserId;
    @Column(name = "SysFinalApprovedDate")
    private Date sysFinalApprovedDate;
    @Column(name = "RemarksByFinalApprover")
    private String remarksByFinalApprover;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "EditedBy")
    private String editedBy;
    @Column(name = "CreatedOn")
    private Date createdOn;
    @Column(name = "EditedOn")
    private Date editedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public BigInteger getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(BigInteger referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getArNo() {
        return arNo;
    }

    public void setArNo(String arNo) {
        this.arNo = arNo;
    }

    public String getCmnServiceSectorTypeId() {
        return cmnServiceSectorTypeId;
    }

    public void setCmnServiceSectorTypeId(String cmnServiceSectorTypeId) {
        this.cmnServiceSectorTypeId = cmnServiceSectorTypeId;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getCmnSalutationId() {
        return cmnSalutationId;
    }

    public void setCmnSalutationId(String cmnSalutationId) {
        this.cmnSalutationId = cmnSalutationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmnCountryId() {
        return cmnCountryId;
    }

    public void setCmnCountryId(String cmnCountryId) {
        this.cmnCountryId = cmnCountryId;
    }

    public String getCmnDzongkhagId() {
        return cmnDzongkhagId;
    }

    public void setCmnDzongkhagId(String cmnDzongkhagId) {
        this.cmnDzongkhagId = cmnDzongkhagId;
    }

    public String getGewog() {
        return gewog;
    }

    public void setGewog(String gewog) {
        this.gewog = gewog;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getTpn() {
        return tpn;
    }

    public void setTpn(String tpn) {
        this.tpn = tpn;
    }

    public String getCmnQualificationId() {
        return cmnQualificationId;
    }

    public void setCmnQualificationId(String cmnQualificationId) {
        this.cmnQualificationId = cmnQualificationId;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public String getCmnUniversityCountryId() {
        return cmnUniversityCountryId;
    }

    public void setCmnUniversityCountryId(String cmnUniversityCountryId) {
        this.cmnUniversityCountryId = cmnUniversityCountryId;
    }

    public String getCmnApplicationRegistrationStatusId() {
        return cmnApplicationRegistrationStatusId;
    }

    public void setCmnApplicationRegistrationStatusId(String cmnApplicationRegistrationStatusId) {
        this.cmnApplicationRegistrationStatusId = cmnApplicationRegistrationStatusId;
    }

    public String getBlacklistedRemarks() {
        return blacklistedRemarks;
    }

    public void setBlacklistedRemarks(String blacklistedRemarks) {
        this.blacklistedRemarks = blacklistedRemarks;
    }

    public String getDeregisteredRemarks() {
        return deregisteredRemarks;
    }

    public void setDeregisteredRemarks(String deregisteredRemarks) {
        this.deregisteredRemarks = deregisteredRemarks;
    }

    public Date getDeRegisteredDate() {
        return deRegisteredDate;
    }

    public void setDeRegisteredDate(Date deRegisteredDate) {
        this.deRegisteredDate = deRegisteredDate;
    }

    public Date getBlacklistedDate() {
        return blacklistedDate;
    }

    public void setBlacklistedDate(Date blacklistedDate) {
        this.blacklistedDate = blacklistedDate;
    }

    public Date getReRegistrationDate() {
        return reRegistrationDate;
    }

    public void setReRegistrationDate(Date reRegistrationDate) {
        this.reRegistrationDate = reRegistrationDate;
    }

    public String getReRegistrationRemarks() {
        return reRegistrationRemarks;
    }

    public void setReRegistrationRemarks(String reRegistrationRemarks) {
        this.reRegistrationRemarks = reRegistrationRemarks;
    }

    public Date getRegistrationApprovedDate() {
        return registrationApprovedDate;
    }

    public void setRegistrationApprovedDate(Date registrationApprovedDate) {
        this.registrationApprovedDate = registrationApprovedDate;
    }

    public Date getRegistrationExpiryDate() {
        return registrationExpiryDate;
    }

    public void setRegistrationExpiryDate(Date registrationExpiryDate) {
        this.registrationExpiryDate = registrationExpiryDate;
    }

    public String getSysFinalApproverUserId() {
        return sysFinalApproverUserId;
    }

    public void setSysFinalApproverUserId(String sysFinalApproverUserId) {
        this.sysFinalApproverUserId = sysFinalApproverUserId;
    }

    public Date getSysFinalApprovedDate() {
        return sysFinalApprovedDate;
    }

    public void setSysFinalApprovedDate(Date sysFinalApprovedDate) {
        this.sysFinalApprovedDate = sysFinalApprovedDate;
    }

    public String getRemarksByFinalApprover() {
        return remarksByFinalApprover;
    }

    public void setRemarksByFinalApprover(String remarksByFinalApprover) {
        this.remarksByFinalApprover = remarksByFinalApprover;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getEditedOn() {
        return editedOn;
    }

    public void setEditedOn(Date editedOn) {
        this.editedOn = editedOn;
    }
}
