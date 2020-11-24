package com.ngn.spring.project.cdb.consultant.model;

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ==================================================================================
 * Created by user on 10/6/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpconsultantfinal")
public class ConsultantFinal extends BaseModel implements Serializable {
    @Id
    //@GeneratedValue
    @Column(name = "Id")
    private String id;
    @Column(name ="SysUserId")
    private String sysUserId;
    @Column(name ="ReferenceNo")
    private String referenceNo;
    @Column(name ="InitialDate")
    private Date initialDate;

    @Column(name ="ApplicationDate")
    private Date applicationDate;
    @Column(name ="CDBNo")
    private String cdbNo;
    @Column(name ="CmnOwnershipTypeId")
    private String ownershipTypeId;
    @Column(name ="NameOfFirm")
    private String firmName;
    @Column(name ="RegisteredAddress")
    private String regAddress;
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
    @Column(name ="tpn")
    private String tpn;

    /*@Column(name ="RegistrationStatus")
    private String regStatus;*/
    @Column(name ="CmnApplicationRegistrationStatusId")
    private String appStatusId;
    @Column(name ="RegistrationApprovedDate")
    private Date regApprovedDate;
    @Column(name ="RegistrationExpiryDate")
    private Date regExpiryDate;
    @Column(name ="DeRegisteredDate")
    private Date deRegisteredDate;
    @Column(name ="BlacklistedDate")
    private Date blacklistedDate;
    @Column(name ="BlacklistedRemarks")
    private String blacklistedRemarks;
    @Column(name ="DeregisteredRemarks")
    private String deregisteredRemarks;
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
    @Column(name ="RevokedDate")
    private Date revokedDate;
    @Column(name ="RevokedRemarks")
    private String revokedRemarks;
    @Column(name ="ReRegistrationDate")
    private Date reRegistrationDate;
    @Column(name ="ReRegistrationRemarks")
    private String reRegistrationRemarks;
    @Column(name ="SurrenderedDate")
    private Date surrenderedDate;
    @Column(name ="SurrenderedRemarks")
    private String surrenderedRemarks;

    @Transient
    private String ownershipName;
    @Transient
    private String regDzongkhagName;
    @Transient
    private String countryName;
    @Transient
    private String dzongkhagName;

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

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
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

    public String getAppStatusId() {
        return appStatusId;
    }

    public void setAppStatusId(String appStatusId) {
        this.appStatusId = appStatusId;
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

    public Date getRevokedDate() {
        return revokedDate;
    }

    public void setRevokedDate(Date revokedDate) {
        this.revokedDate = revokedDate;
    }

    public String getRevokedRemarks() {
        return revokedRemarks;
    }

    public void setRevokedRemarks(String revokedRemarks) {
        this.revokedRemarks = revokedRemarks;
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

    public Date getSurrenderedDate() {
        return surrenderedDate;
    }

    public void setSurrenderedDate(Date surrenderedDate) {
        this.surrenderedDate = surrenderedDate;
    }

    public String getSurrenderedRemarks() {
        return surrenderedRemarks;
    }

    public void setSurrenderedRemarks(String surrenderedRemarks) {
        this.surrenderedRemarks = surrenderedRemarks;
    }

    public String getOwnershipName() {
        return ownershipName;
    }

    public void setOwnershipName(String ownershipName) {
        this.ownershipName = ownershipName;
    }

    public String getRegDzongkhagName() {
        return regDzongkhagName;
    }

    public void setRegDzongkhagName(String regDzongkhagName) {
        this.regDzongkhagName = regDzongkhagName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDzongkhagName() {
        return dzongkhagName;
    }

    public void setDzongkhagName(String dzongkhagName) {
        this.dzongkhagName = dzongkhagName;
    }
}
