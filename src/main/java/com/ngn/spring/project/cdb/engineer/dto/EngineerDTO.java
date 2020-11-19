package com.ngn.spring.project.cdb.engineer.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Dechen Wangdi on 12/20/2019.
 */
public class EngineerDTO implements Serializable {

    private String id;
    private String serviceTypeId;
    private String serviceTypeText;
    private String trade;
    private String typeText;
    private String tradeText;
    private BigInteger referenceNo;
    private Date initialDate;
    private Date applicationDate;
    private String arNo;
    private String serviceSectorType;
    private String salutation;
    private String salutationText;
    private String name;
    private String countryId;
    private String countryText;
    private String dzongkhagId;
    private String dzongkhagText;
    private String gewog;
    private String gewogText;
    private String village;
    private String villageText;
    private String email;
    private String mobileNo;
    private String employeeName;
    private String employeeAddress;
    private String TPN;
    private String cid;
    private String qualificationId;
    private String qualificationText;
    private String graduationYear;
    private String universityName;
    private String universityCountry;
    private String universityCountryText;
    private Integer registrationStatus;
    private MultipartFile attachmentFile;
    private String documentName;
    private String documentPath;
    private String applicationRegistrationStatusId;
    private String sysVerifierUserId;
    private Date verifiedDate;
    private String remarksByVerifier;
    private String sysApproverUserId;
    private String remarksByApprover;
    private String sysPaymentApproverUserId;
    private String remarksByPaymentApprover;
    private Date paymentApprovedDate;
    private String paymentReceiptNo;
    private Date paymentReceiptDate;
    private String sysRejectorUserId;
    private String RemarksByRejector;
    private Date rejectedDate;
    private String sysRejectionCode;
    private Date registrationApprovedDate;
    private Date registrationExpiryDate;
    private String deregisteredBlacklistedRemarks;
    private String sysFinalApproverUserId;
    private Date sysFinalApprovedDate;
    private String remarksByFinalApprover;
    private String sysLockedByUserId;
    private Boolean hasNotification;
    private Integer waiveOffLateFee;
    private BigDecimal newLateFeeAmount;
    private String appStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeText() {
        return serviceTypeText;
    }

    public void setServiceTypeText(String serviceTypeText) {
        this.serviceTypeText = serviceTypeText;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getTradeText() {
        return tradeText;
    }

    public void setTradeText(String tradeText) {
        this.tradeText = tradeText;
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

    public String getServiceSectorType() {
        return serviceSectorType;
    }

    public void setServiceSectorType(String serviceSectorType) {
        this.serviceSectorType = serviceSectorType;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getSalutationText() {
        return salutationText;
    }

    public void setSalutationText(String salutationText) {
        this.salutationText = salutationText;
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

    public String getCountryText() {
        return countryText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    public String getDzongkhagId() {
        return dzongkhagId;
    }

    public void setDzongkhagId(String dzongkhagId) {
        this.dzongkhagId = dzongkhagId;
    }

    public String getDzongkhagText() {
        return dzongkhagText;
    }

    public void setDzongkhagText(String dzongkhagText) {
        this.dzongkhagText = dzongkhagText;
    }

    public String getGewog() {
        return gewog;
    }

    public void setGewog(String gewog) {
        this.gewog = gewog;
    }

    public String getGewogText() {
        return gewogText;
    }

    public void setGewogText(String gewogText) {
        this.gewogText = gewogText;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillageText() {
        return villageText;
    }

    public void setVillageText(String villageText) {
        this.villageText = villageText;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getTPN() {
        return TPN;
    }

    public void setTPN(String TPN) {
        this.TPN = TPN;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationText() {
        return qualificationText;
    }

    public void setQualificationText(String qualificationText) {
        this.qualificationText = qualificationText;
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

    public String getUniversityCountryText() {
        return universityCountryText;
    }

    public void setUniversityCountryText(String universityCountryText) {
        this.universityCountryText = universityCountryText;
    }

    public Integer getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Integer registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public MultipartFile getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(MultipartFile attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getApplicationRegistrationStatusId() {
        return applicationRegistrationStatusId;
    }

    public void setApplicationRegistrationStatusId(String applicationRegistrationStatusId) {
        this.applicationRegistrationStatusId = applicationRegistrationStatusId;
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
        return RemarksByRejector;
    }

    public void setRemarksByRejector(String remarksByRejector) {
        RemarksByRejector = remarksByRejector;
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

    public String getSysLockedByUserId() {
        return sysLockedByUserId;
    }

    public void setSysLockedByUserId(String sysLockedByUserId) {
        this.sysLockedByUserId = sysLockedByUserId;
    }

    public Boolean getHasNotification() {
        return hasNotification;
    }

    public void setHasNotification(Boolean hasNotification) {
        this.hasNotification = hasNotification;
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

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }
}
