package com.ngn.spring.project.cdb.contractor.registration.dto;

import java.math.BigInteger;
import java.util.Date;

/**
 * ====================================================================
 * Created by Nima Yoezer on 7/18/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class ContractorDTOFetch {
    private String id;
    private String contractorId;
    private BigInteger referenceNo;
    private Date initialDate;

    private Date applicationDate;
    private String cdbNo;
    private String ownershipTypeId;
    private String firmName;
    private String regAddress;
    private String regDzongkhagId;

    private String pVillageId;
    private String pGewogId;
    private String estAddress;
    private String pCountryId;
    private String pDzongkhagId;
    private String regEmail;
    private String regPhoneNo;
    private String regMobileNo;
    private String regFaxNo;
    private String tradeLicenseNo;
    private String tpn;

    private String regStatus;
    private String appStatusId;

    private String sysVerifierUserId;
    private Date regVerifiedDate;
    private String verifierRemarks;

    private String paymentReceiptNo;
    private Date paymentReceiptDate;
    private Integer waiveOffLateFee;

    private String approverUserId;
    private String approverRemarks;
    private String paymentApproverUserId;
    private Date paymentApprovedDate;
    private String PaymentApproverRemark;

    private String rejectorRemarks;
    private String rejecterUserId;
    private Date rejectedDate;
    private String rejectionCode;

    private Date regApprovedDate;
    private Date regExpiryDate;
    private Date regPaymentApprovedDate;
    private String dRegBlacklistedRemarks;
    private String ownershipChangeRemarks;
    private String finalApproverUserId;

    private Date finalApproveDate;
    private String finalApproverRemarks;
    private String lockedByUserId;
    private String hasNotification;
    private String locChangeReason;

    private String appStatusName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
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

    public String getOwnershipTypeId() {
        return ownershipTypeId;
    }

    public void setOwnershipTypeId(String ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getRegDzongkhagId() {
        return regDzongkhagId;
    }

    public void setRegDzongkhagId(String regDzongkhagId) {
        this.regDzongkhagId = regDzongkhagId;
    }

    public String getpVillageId() {
        return pVillageId;
    }

    public void setpVillageId(String pVillageId) {
        this.pVillageId = pVillageId;
    }

    public String getpGewogId() {
        return pGewogId;
    }

    public void setpGewogId(String pGewogId) {
        this.pGewogId = pGewogId;
    }

    public String getEstAddress() {
        return estAddress;
    }

    public void setEstAddress(String estAddress) {
        this.estAddress = estAddress;
    }

    public String getpCountryId() {
        return pCountryId;
    }

    public void setpCountryId(String pCountryId) {
        this.pCountryId = pCountryId;
    }

    public String getpDzongkhagId() {
        return pDzongkhagId;
    }

    public void setpDzongkhagId(String pDzongkhagId) {
        this.pDzongkhagId = pDzongkhagId;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegPhoneNo() {
        return regPhoneNo;
    }

    public void setRegPhoneNo(String regPhoneNo) {
        this.regPhoneNo = regPhoneNo;
    }

    public String getRegMobileNo() {
        return regMobileNo;
    }

    public void setRegMobileNo(String regMobileNo) {
        this.regMobileNo = regMobileNo;
    }

    public String getRegFaxNo() {
        return regFaxNo;
    }

    public void setRegFaxNo(String regFaxNo) {
        this.regFaxNo = regFaxNo;
    }

    public String getTradeLicenseNo() {
        return tradeLicenseNo;
    }

    public void setTradeLicenseNo(String tradeLicenseNo) {
        this.tradeLicenseNo = tradeLicenseNo;
    }

    public String getTpn() {
        return tpn;
    }

    public void setTpn(String tpn) {
        this.tpn = tpn;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getAppStatusId() {
        return appStatusId;
    }

    public void setAppStatusId(String appStatusId) {
        this.appStatusId = appStatusId;
    }

    public String getSysVerifierUserId() {
        return sysVerifierUserId;
    }

    public void setSysVerifierUserId(String sysVerifierUserId) {
        this.sysVerifierUserId = sysVerifierUserId;
    }

    public Date getRegVerifiedDate() {
        return regVerifiedDate;
    }

    public void setRegVerifiedDate(Date regVerifiedDate) {
        this.regVerifiedDate = regVerifiedDate;
    }

    public String getVerifierRemarks() {
        return verifierRemarks;
    }

    public void setVerifierRemarks(String verifierRemarks) {
        this.verifierRemarks = verifierRemarks;
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

    public Integer getWaiveOffLateFee() {
        return waiveOffLateFee;
    }

    public void setWaiveOffLateFee(Integer waiveOffLateFee) {
        this.waiveOffLateFee = waiveOffLateFee;
    }

    public String getApproverUserId() {
        return approverUserId;
    }

    public void setApproverUserId(String approverUserId) {
        this.approverUserId = approverUserId;
    }

    public String getApproverRemarks() {
        return approverRemarks;
    }

    public void setApproverRemarks(String approverRemarks) {
        this.approverRemarks = approverRemarks;
    }

    public String getPaymentApproverUserId() {
        return paymentApproverUserId;
    }

    public void setPaymentApproverUserId(String paymentApproverUserId) {
        this.paymentApproverUserId = paymentApproverUserId;
    }

    public Date getPaymentApprovedDate() {
        return paymentApprovedDate;
    }

    public void setPaymentApprovedDate(Date paymentApprovedDate) {
        this.paymentApprovedDate = paymentApprovedDate;
    }

    public String getPaymentApproverRemark() {
        return PaymentApproverRemark;
    }

    public void setPaymentApproverRemark(String paymentApproverRemark) {
        PaymentApproverRemark = paymentApproverRemark;
    }

    public String getRejectorRemarks() {
        return rejectorRemarks;
    }

    public void setRejectorRemarks(String rejectorRemarks) {
        this.rejectorRemarks = rejectorRemarks;
    }

    public String getRejecterUserId() {
        return rejecterUserId;
    }

    public void setRejecterUserId(String rejecterUserId) {
        this.rejecterUserId = rejecterUserId;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public String getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionCode(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public Date getRegApprovedDate() {
        return regApprovedDate;
    }

    public void setRegApprovedDate(Date regApprovedDate) {
        this.regApprovedDate = regApprovedDate;
    }

    public Date getRegExpiryDate() {
        return regExpiryDate;
    }

    public void setRegExpiryDate(Date regExpiryDate) {
        this.regExpiryDate = regExpiryDate;
    }

    public Date getRegPaymentApprovedDate() {
        return regPaymentApprovedDate;
    }

    public void setRegPaymentApprovedDate(Date regPaymentApprovedDate) {
        this.regPaymentApprovedDate = regPaymentApprovedDate;
    }

    public String getdRegBlacklistedRemarks() {
        return dRegBlacklistedRemarks;
    }

    public void setdRegBlacklistedRemarks(String dRegBlacklistedRemarks) {
        this.dRegBlacklistedRemarks = dRegBlacklistedRemarks;
    }

    public String getOwnershipChangeRemarks() {
        return ownershipChangeRemarks;
    }

    public void setOwnershipChangeRemarks(String ownershipChangeRemarks) {
        this.ownershipChangeRemarks = ownershipChangeRemarks;
    }

    public String getFinalApproverUserId() {
        return finalApproverUserId;
    }

    public void setFinalApproverUserId(String finalApproverUserId) {
        this.finalApproverUserId = finalApproverUserId;
    }

    public Date getFinalApproveDate() {
        return finalApproveDate;
    }

    public void setFinalApproveDate(Date finalApproveDate) {
        this.finalApproveDate = finalApproveDate;
    }

    public String getFinalApproverRemarks() {
        return finalApproverRemarks;
    }

    public void setFinalApproverRemarks(String finalApproverRemarks) {
        this.finalApproverRemarks = finalApproverRemarks;
    }

    public String getLockedByUserId() {
        return lockedByUserId;
    }

    public void setLockedByUserId(String lockedByUserId) {
        this.lockedByUserId = lockedByUserId;
    }

    public String getHasNotification() {
        return hasNotification;
    }

    public void setHasNotification(String hasNotification) {
        this.hasNotification = hasNotification;
    }

    public String getLocChangeReason() {
        return locChangeReason;
    }

    public void setLocChangeReason(String locChangeReason) {
        this.locChangeReason = locChangeReason;
    }

    public String getAppStatusName() {
        return appStatusName;
    }

    public void setAppStatusName(String appStatusName) {
        this.appStatusName = appStatusName;
    }
}
