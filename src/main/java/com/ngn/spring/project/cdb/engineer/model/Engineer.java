package com.ngn.spring.project.cdb.engineer.model;

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

/**
 * Created by User on 12/20/2019.
 */

@Entity
@Table(name = "crpengineer")
public class Engineer extends BaseModel implements Serializable {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpEngineerId")
    private String engineerId;

    @Column(name = "ReferenceNo")
    private BigInteger referenceNo;
    @Column(name = "InitialDate")
    private Date initialDate;

    @Column(name = "ApplicationDate")
    private Date applicationDate;

    @Column(name = "CDBNo")
    private String cdbNo;

    @Column(name = "CmnServiceSectorTypeId")
    private String cmnServiceSectorTypeId;

    @Column(name = "CmnTradeId")
    private String tradeId;

    @Column(name = "CIDNo")
    private String CID;

    @Column(name = "CmnSalutationId")
    private String salutationId;

    @Column(name = "Name")
    private String name;

    @Column(name = "CmnCountryId")
    private String countryId;

    @Column(name = "CmnDzongkhagId")
    private String dzongkhagId;

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
    private String TPN;

    @Column(name = "CmnQualificationId")
    private String qualification;

    @Column(name = "GraduationYear")
    private String graduationYear;

    @Column(name = "NameOfUniversity")
    private String universityName;

    @Column(name = "CmnUniversityCountryId")
    private String universityCountry;

    @Column(name = "RegistrationStatus")
    private Integer registrationStatus;

    @Column(name = "CmnApplicationRegistrationStatusId")
    private String cmnApplicationRegistrationStatusId;

    @Column(name = "SysVerifierUserId")
    private String sysVerifierUserId;

    @Column(name = "VerifiedDate")
    private Date verifiedDate;

    @Column(name = "RemarksByVerifier")
    private String remarksByVerifier;

    @Column(name = "SysApproverUserId")
    private String sysApproverUserId;

    @Column(name = "RemarksByApprover")
    private String remarksByApprover;

    @Column(name = "SysPaymentApproverUserId")
    private String sysPaymentApproverUserId;

    @Column(name = "PaymaentApprovedDate")
    private Date paymentApprovedDate;

    @Column(name = "RemarksByPaymentApprover")
    private String remarksByPaymentApprover;

    @Column(name = "PaymentReceiptNo")
    private String paymentReceiptNo;

    @Column(name = "PaymentReceiptDate")
    private Date paymentReceiptDate;

    @Column(name = "SysRejectorUserId")
    private String sysRejectorUserId;

    @Column(name = "RemarksByRejector")
    private String remarksByRejector;

    @Column(name = "RejectedDate")
    private Date rejectedDate;

    @Column(name = "SysRejectionCode")
    private String sysRejectionCode;

    @Column(name = "RegistrationApprovedDate")
    private Date registrationApprovedDate;

    @Column(name = "RegistrationExpiryDate")
    private Date registrationExpiryDate;

    @Column(name = "DeregisteredBlacklistedRemarks")
    private String deregisteredBlacklistedRemarks;

    @Column(name = "SysLockedByUserId")
    private String sysLockedByUserId;

    /*@Column(name = "HasNotification")
    private Integer hasNotification;*/

    @Column(name = "WaiveOffLateFee")
    private Integer waiveOffLateFee;

    @Column(name = "NewLateFeeAmount")
    private BigDecimal newLateFeeAmount;

    @Column(name = "DocumentName")
    private String documentName;

    @Column(name = "CancellationReason")
    private String cancellationReason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
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

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public String getCmnServiceSectorTypeId() {
        return cmnServiceSectorTypeId;
    }

    public void setCmnServiceSectorTypeId(String cmnServiceSectorTypeId) {
        this.cmnServiceSectorTypeId = cmnServiceSectorTypeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(String salutationId) {
        this.salutationId = salutationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getDzongkhagId() {
        return dzongkhagId;
    }

    public void setDzongkhagId(String dzongkhagId) {
        this.dzongkhagId = dzongkhagId;
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

    public String getTPN() {
        return TPN;
    }

    public void setTPN(String TPN) {
        this.TPN = TPN;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityCountry() {
        return universityCountry;
    }

    public void setUniversityCountry(String universityCountry) {
        this.universityCountry = universityCountry;
    }

    public Integer getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Integer registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getCmnApplicationRegistrationStatusId() {
        return cmnApplicationRegistrationStatusId;
    }

    public void setCmnApplicationRegistrationStatusId(String cmnApplicationRegistrationStatusId) {
        this.cmnApplicationRegistrationStatusId = cmnApplicationRegistrationStatusId;
    }

    public String getSysVerifierUserId() {
        return sysVerifierUserId;
    }

    public void setSysVerifierUserId(String sysVerifierUserId) {
        this.sysVerifierUserId = sysVerifierUserId;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getRemarksByVerifier() {
        return remarksByVerifier;
    }

    public void setRemarksByVerifier(String remarksByVerifier) {
        this.remarksByVerifier = remarksByVerifier;
    }

    public String getSysApproverUserId() {
        return sysApproverUserId;
    }

    public void setSysApproverUserId(String sysApproverUserId) {
        this.sysApproverUserId = sysApproverUserId;
    }

    public String getRemarksByApprover() {
        return remarksByApprover;
    }

    public void setRemarksByApprover(String remarksByApprover) {
        this.remarksByApprover = remarksByApprover;
    }

    public String getSysPaymentApproverUserId() {
        return sysPaymentApproverUserId;
    }

    public void setSysPaymentApproverUserId(String sysPaymentApproverUserId) {
        this.sysPaymentApproverUserId = sysPaymentApproverUserId;
    }

    public String getRemarksByPaymentApprover() {
        return remarksByPaymentApprover;
    }

    public void setRemarksByPaymentApprover(String remarksByPaymentApprover) {
        this.remarksByPaymentApprover = remarksByPaymentApprover;
    }

    public Date getPaymentApprovedDate() {
        return paymentApprovedDate;
    }

    public void setPaymentApprovedDate(Date paymentApprovedDate) {
        this.paymentApprovedDate = paymentApprovedDate;
    }

    public String getPaymentReceiptNo() {
        return paymentReceiptNo;
    }

    public void setPaymentReceiptNo(String paymentReceiptNo) {
        this.paymentReceiptNo = paymentReceiptNo;
    }

    public Date getPaymentReceiptDate() {
        return paymentReceiptDate;
    }

    public void setPaymentReceiptDate(Date paymentReceiptDate) {
        this.paymentReceiptDate = paymentReceiptDate;
    }

    public String getSysRejectorUserId() {
        return sysRejectorUserId;
    }

    public void setSysRejectorUserId(String sysRejectorUserId) {
        this.sysRejectorUserId = sysRejectorUserId;
    }

    public String getRemarksByRejector() {
        return remarksByRejector;
    }

    public void setRemarksByRejector(String remarksByRejector) {
        this.remarksByRejector = remarksByRejector;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public String getSysRejectionCode() {
        return sysRejectionCode;
    }

    public void setSysRejectionCode(String sysRejectionCode) {
        this.sysRejectionCode = sysRejectionCode;
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

    public String getDeregisteredBlacklistedRemarks() {
        return deregisteredBlacklistedRemarks;
    }

    public void setDeregisteredBlacklistedRemarks(String deregisteredBlacklistedRemarks) {
        this.deregisteredBlacklistedRemarks = deregisteredBlacklistedRemarks;
    }

    public String getSysLockedByUserId() {
        return sysLockedByUserId;
    }

    public void setSysLockedByUserId(String sysLockedByUserId) {
        this.sysLockedByUserId = sysLockedByUserId;
    }

    public Integer getWaiveOffLateFee() {
        return waiveOffLateFee;
    }

    public void setWaiveOffLateFee(Integer waiveOffLateFee) {
        this.waiveOffLateFee = waiveOffLateFee;
    }

    public BigDecimal getNewLateFeeAmount() {
        return newLateFeeAmount;
    }

    public void setNewLateFeeAmount(BigDecimal newLateFeeAmount) {
        this.newLateFeeAmount = newLateFeeAmount;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
