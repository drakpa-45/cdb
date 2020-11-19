package com.ngn.spring.project.cdb.survey.entity;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by USER on 3/23/2020.
 */

@Entity
@Table(name = "crpsurvey")
public class CrpsurveyEntity implements Serializable {
    @Id
    @Column(name = "Id")
    private String id;
    private String CrpSurveyId;
    private String ReferenceNo;
    private Date InitialDate;
    private Date ApplicationDate;
    private String ARNo;
    private String CmnServiceSectorTypeId;
    private String CmnTradeId;
    private String CIDNo;
    private String CmnSalutationId;
    private String Name;
    private String CmnCountryId;
    private String CmnDzongkhagId;
    private String Gewog;
    private String Village;
    private String Email;
    private String MobileNo;
    private String EmployerName;
    private String EmployerAddress;
    private String TPN;
    private String CmnQualificationId;
    private String GraduationYear;
    private String NameOfUniversity;
    private String CmnUniversityCountryId;
    private String RegistrationStatus;
    private String CmnApplicationRegistrationStatusId;
    private String SysVerifierUserId;
    private DateTime VerifiedDate;
    private String RemarksByVerifier;
    private String SysApproverUserId;
    private String RemarksByApprover;
    private String SysPaymentApproverUserId;
    private String RemarksByPaymentApprover;
    private String PaymentApprovedDate;
    private String PaymentReceiptNo;
    private Date PaymentReceiptDate;
    private String SysRejectorUserId;
    private String RemarksByRejector;
    private String RejectedDate;
    private String SysRejectionCode;
    private DateTime RegistrationApprovedDate;
    private Date RegistrationExpiryDate;
    private String DeregisteredBlacklistedRemarks;
    private String SysFinalApproverUserId;
    private DateTime SysFinalApprovedDate;
    private String RemarksByFinalApprover;
    private String SysLockedByUserId;
    private String CreatedBy;
    private String EditedBy;
    private Date CreatedOn;
    private Date EditedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrpSurveyId() {
        return CrpSurveyId;
    }

    public void setCrpSurveyId(String crpSurveyId) {
        CrpSurveyId = crpSurveyId;
    }

    public String getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        ReferenceNo = referenceNo;
    }

    public Date getInitialDate() {
        return InitialDate;
    }

    public void setInitialDate(Date initialDate) {
        InitialDate = initialDate;
    }

    public Date getApplicationDate() {
        return ApplicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        ApplicationDate = applicationDate;
    }

    public String getARNo() {
        return ARNo;
    }

    public void setARNo(String ARNo) {
        this.ARNo = ARNo;
    }

    public String getCmnServiceSectorTypeId() {
        return CmnServiceSectorTypeId;
    }

    public void setCmnServiceSectorTypeId(String cmnServiceSectorTypeId) {
        CmnServiceSectorTypeId = cmnServiceSectorTypeId;
    }

    public String getCmnTradeId() {
        return CmnTradeId;
    }

    public void setCmnTradeId(String cmnTradeId) {
        CmnTradeId = cmnTradeId;
    }

    public String getCIDNo() {
        return CIDNo;
    }

    public void setCIDNo(String CIDNo) {
        this.CIDNo = CIDNo;
    }

    public String getCmnSalutationId() {
        return CmnSalutationId;
    }

    public void setCmnSalutationId(String cmnSalutationId) {
        CmnSalutationId = cmnSalutationId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCmnCountryId() {
        return CmnCountryId;
    }

    public void setCmnCountryId(String cmnCountryId) {
        CmnCountryId = cmnCountryId;
    }

    public String getCmnDzongkhagId() {
        return CmnDzongkhagId;
    }

    public void setCmnDzongkhagId(String cmnDzongkhagId) {
        CmnDzongkhagId = cmnDzongkhagId;
    }

    public String getGewog() {
        return Gewog;
    }

    public void setGewog(String gewog) {
        Gewog = gewog;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public void setEmployerName(String employerName) {
        EmployerName = employerName;
    }

    public String getEmployerAddress() {
        return EmployerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        EmployerAddress = employerAddress;
    }

    public String getTPN() {
        return TPN;
    }

    public void setTPN(String TPN) {
        this.TPN = TPN;
    }

    public String getCmnQualificationId() {
        return CmnQualificationId;
    }

    public void setCmnQualificationId(String cmnQualificationId) {
        CmnQualificationId = cmnQualificationId;
    }

    public String getGraduationYear() {
        return GraduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        GraduationYear = graduationYear;
    }

    public String getNameOfUniversity() {
        return NameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity) {
        NameOfUniversity = nameOfUniversity;
    }

    public String getCmnUniversityCountryId() {
        return CmnUniversityCountryId;
    }

    public void setCmnUniversityCountryId(String cmnUniversityCountryId) {
        CmnUniversityCountryId = cmnUniversityCountryId;
    }

    public String getRegistrationStatus() {
        return RegistrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        RegistrationStatus = registrationStatus;
    }

    public String getCmnApplicationRegistrationStatusId() {
        return CmnApplicationRegistrationStatusId;
    }

    public void setCmnApplicationRegistrationStatusId(String cmnApplicationRegistrationStatusId) {
        CmnApplicationRegistrationStatusId = cmnApplicationRegistrationStatusId;
    }

    public String getSysVerifierUserId() {
        return SysVerifierUserId;
    }

    public void setSysVerifierUserId(String sysVerifierUserId) {
        SysVerifierUserId = sysVerifierUserId;
    }

    public DateTime getVerifiedDate() {
        return VerifiedDate;
    }

    public void setVerifiedDate(DateTime verifiedDate) {
        VerifiedDate = verifiedDate;
    }

    public String getRemarksByVerifier() {
        return RemarksByVerifier;
    }

    public void setRemarksByVerifier(String remarksByVerifier) {
        RemarksByVerifier = remarksByVerifier;
    }

    public String getSysApproverUserId() {
        return SysApproverUserId;
    }

    public void setSysApproverUserId(String sysApproverUserId) {
        SysApproverUserId = sysApproverUserId;
    }

    public String getRemarksByApprover() {
        return RemarksByApprover;
    }

    public void setRemarksByApprover(String remarksByApprover) {
        RemarksByApprover = remarksByApprover;
    }

    public String getSysPaymentApproverUserId() {
        return SysPaymentApproverUserId;
    }

    public void setSysPaymentApproverUserId(String sysPaymentApproverUserId) {
        SysPaymentApproverUserId = sysPaymentApproverUserId;
    }

    public String getRemarksByPaymentApprover() {
        return RemarksByPaymentApprover;
    }

    public void setRemarksByPaymentApprover(String remarksByPaymentApprover) {
        RemarksByPaymentApprover = remarksByPaymentApprover;
    }

    public String getPaymentApprovedDate() {
        return PaymentApprovedDate;
    }

    public void setPaymentApprovedDate(String paymentApprovedDate) {
        PaymentApprovedDate = paymentApprovedDate;
    }

    public String getPaymentReceiptNo() {
        return PaymentReceiptNo;
    }

    public void setPaymentReceiptNo(String paymentReceiptNo) {
        PaymentReceiptNo = paymentReceiptNo;
    }

    public Date getPaymentReceiptDate() {
        return PaymentReceiptDate;
    }

    public void setPaymentReceiptDate(Date paymentReceiptDate) {
        PaymentReceiptDate = paymentReceiptDate;
    }

    public String getSysRejectorUserId() {
        return SysRejectorUserId;
    }

    public void setSysRejectorUserId(String sysRejectorUserId) {
        SysRejectorUserId = sysRejectorUserId;
    }

    public String getRemarksByRejector() {
        return RemarksByRejector;
    }

    public void setRemarksByRejector(String remarksByRejector) {
        RemarksByRejector = remarksByRejector;
    }

    public String getRejectedDate() {
        return RejectedDate;
    }

    public void setRejectedDate(String rejectedDate) {
        RejectedDate = rejectedDate;
    }

    public String getSysRejectionCode() {
        return SysRejectionCode;
    }

    public void setSysRejectionCode(String sysRejectionCode) {
        SysRejectionCode = sysRejectionCode;
    }

    public DateTime getRegistrationApprovedDate() {
        return RegistrationApprovedDate;
    }

    public void setRegistrationApprovedDate(DateTime registrationApprovedDate) {
        RegistrationApprovedDate = registrationApprovedDate;
    }

    public Date getRegistrationExpiryDate() {
        return RegistrationExpiryDate;
    }

    public void setRegistrationExpiryDate(Date registrationExpiryDate) {
        RegistrationExpiryDate = registrationExpiryDate;
    }

    public String getDeregisteredBlacklistedRemarks() {
        return DeregisteredBlacklistedRemarks;
    }

    public void setDeregisteredBlacklistedRemarks(String deregisteredBlacklistedRemarks) {
        DeregisteredBlacklistedRemarks = deregisteredBlacklistedRemarks;
    }

    public String getSysFinalApproverUserId() {
        return SysFinalApproverUserId;
    }

    public void setSysFinalApproverUserId(String sysFinalApproverUserId) {
        SysFinalApproverUserId = sysFinalApproverUserId;
    }

    public DateTime getSysFinalApprovedDate() {
        return SysFinalApprovedDate;
    }

    public void setSysFinalApprovedDate(DateTime sysFinalApprovedDate) {
        SysFinalApprovedDate = sysFinalApprovedDate;
    }

    public String getRemarksByFinalApprover() {
        return RemarksByFinalApprover;
    }

    public void setRemarksByFinalApprover(String remarksByFinalApprover) {
        RemarksByFinalApprover = remarksByFinalApprover;
    }

    public String getSysLockedByUserId() {
        return SysLockedByUserId;
    }

    public void setSysLockedByUserId(String sysLockedByUserId) {
        SysLockedByUserId = sysLockedByUserId;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getEditedBy() {
        return EditedBy;
    }

    public void setEditedBy(String editedBy) {
        EditedBy = editedBy;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date createdOn) {
        CreatedOn = createdOn;
    }

    public Date getEditedOn() {
        return EditedOn;
    }

    public void setEditedOn(Date editedOn) {
        EditedOn = editedOn;
    }
}
