package com.ngn.spring.project.cdb.specializedFirm.model;

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpspecializedtrade")
public class SpecializedFirm extends BaseModel implements Serializable{
    @Id
    //@GeneratedValue
    @Column(name = "Id")
    private String id;
    @Column(name ="CrpSpecializedTradeId")
    private String crpSpecializedTradeId;
    @Column(name ="ReferenceNo")
    private String ReferenceNo;
    @Column(name ="InitialDate")
    private Date initialDate;

    @Column(name ="ApplicationDate")
    private Date applicationDate;
    @Column(name ="SPNo")
    private String cdbNo;
    @Column(name ="CmnOwnershipTypeId")
    private String ownershipTypeId;
    @Column(name ="NameOfFirm")
    private String firmName;
    @Column(name ="Edit_Name_Of_Firm")
    private String newFirmName;
    @Column(name ="RegisteredAddress")
    private String regAddress;
    @Column(name ="Edit_Correspondence_Addr")
    private String editEstbAddress;

    @Column(name ="Edit_corr_dz_sl_no")
    private String newRegDzoId;
    @Column(name ="CmnRegisteredDzongkhagId")
    private String regDzongkhagId;


    @Column(name ="Village")
    private String pVillageId;
    @Column(name ="Gewog")
    private String pGewogId;
    @Column(name ="Address")
    private String estAddress;
    @Column(name ="CmnCountryId")
    private String pCountryId;
    @Column(name ="CmnDzongkhagId")
    private String pDzongkhagId;
    @Column(name ="Email")
    private String regEmail;
    @Column(name ="TelephoneNo")
    private String regPhoneNo;
    @Column(name ="MobileNo")
    private String regMobileNo;
    @Column(name ="FaxNo")
    private String regFaxNo;
    @Column(name ="TradeLicenseNo")
    private String tradeLicenseNo;
    @Column(name ="TPN")
    private String tpn;

    @Column(name ="RegistrationStatus")
    private String regStatus;
    @Column(name ="CmnApplicationRegistrationStatusId")
    private String appStatusId;

    @Column(name ="SysVerifierUserId")
    private String sysVerifierUserId;
    @Column(name ="VerifiedDate")
    private Date verifiedDate;
    @Column(name ="RemarksByVerifier")
    private String verifierRemarks;

    @Column(name ="PaymentReceiptNo")
    private String paymentReceiptNo;
    @Column(name ="PaymentReceiptDate")
    private Date paymentReceiptDate;
    @Column(name ="WaiveOffLateFee")
    private BigDecimal waiveOffLateFee;
    @Column(name ="NewLateFeeAmount")
    private Integer newLateFeeAmount;

    @Column(name ="SysApproverUserId")
    private String approverUserId;
    @Column(name ="RemarksByApprover")
    private String approverRemarks;
    @Column(name ="SysPaymentApproverUserId")
    private String paymentApproverUserId;
    @Column(name ="PaymentApprovedDate")
    private Date paymentApprovedDate;
    @Column(name ="RemarksByPaymentApprover")
    private String PaymentApproverRemark;

    @Column(name ="RemarksByRejector")
    private String rejectorRemarks;
    @Column(name ="SysRejecterUserId")
    private String rejecterUserId;
    @Column(name ="RejectedDate")
    private Date rejectedDate;
    @Column(name ="SysRejectionCode")
    private String rejectionCode;

    @Column(name ="RegistrationApprovedDate")
    private Date regApprovedDate;
    @Column(name ="RegistrationExpiryDate")
    private Date regExpiryDate;
    @Column(name ="RegistrationPaymentApprovedDate")
    private Date regPaymentApprovedDate;
    @Column(name ="DeregisteredBlacklistedRemarks")
    private String dRegBlacklistedRemarks;
    @Column(name ="ChangeOfOwnershipRemarks")
    private String ownershipChangeRemarks;

    @Column(name ="SysFinalApproverUserId")
    private String finalApproverUserId;
    @Column(name ="SysFinalApprovedDate")
    private Date finalApproveDate;
    @Column(name ="RemarksByFinalApprover")
    private String finalApproverRemarks;
    @Column(name ="SysLockedByUserId")
    private String lockedByUserId;
    @Column(name ="HasNotification")
    private String hasNotification;
    @Column(name ="LocationChangeReason")
    private String locChangeReason;

    @Transient
    private String confirmEmail;

   @Transient
    private List<SpFirmHR> spFirmHRs;

   @Transient
    private List<SpFirmtHRAttachment> spFirmHRAs;

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrpSpecializedTradeId() {
        return crpSpecializedTradeId;
    }

    public void setCrpSpecializedTradeId(String crpSpecializedTradeId) {
        this.crpSpecializedTradeId = crpSpecializedTradeId;
    }

    public String getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        ReferenceNo = referenceNo;
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

    public String getNewFirmName() {
        return newFirmName;
    }

    public void setNewFirmName(String newFirmName) {
        this.newFirmName = newFirmName;
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

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
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

    public BigDecimal getWaiveOffLateFee() {
        return waiveOffLateFee;
    }

    public void setWaiveOffLateFee(BigDecimal waiveOffLateFee) {
        this.waiveOffLateFee = waiveOffLateFee;
    }

    public Integer getNewLateFeeAmount() {
        return newLateFeeAmount;
    }

    public void setNewLateFeeAmount(Integer newLateFeeAmount) {
        this.newLateFeeAmount = newLateFeeAmount;
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

    public List<SpFirmHR> getSpFirmHRs() {
        return spFirmHRs;
    }

    public void setSpFirmHRs(List<SpFirmHR> spFirmHRs) {
        this.spFirmHRs = spFirmHRs;
    }

    public List<SpFirmtHRAttachment> getSpFirmHRAs() {
        return spFirmHRAs;
    }

    public void setSpFirmHRAs(List<SpFirmtHRAttachment> spFirmHRAs) {
        this.spFirmHRAs = spFirmHRAs;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public String getEditEstbAddress() {
        return editEstbAddress;
    }

    public void setEditEstbAddress(String editEstbAddress) {
        this.editEstbAddress = editEstbAddress;
    }

    public String getNewRegDzoId() {
        return newRegDzoId;
    }

    public void setNewRegDzoId(String newRegDzoId) {
        this.newRegDzoId = newRegDzoId;
    }
}
