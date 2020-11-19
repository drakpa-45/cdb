package com.ngn.spring.project.cdb.trade.dto;

import com.ngn.spring.project.cdb.trade.entity.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by USER on 3/23/2020.
 */
public class TradeDto {
    private String id;
    private String crpSpecializedTradeId;
    private String cdbNo;
    private BigInteger referenceNo;
    private String salutation;
    private String salutationId;
    private String cidNo;
    private String fullname;
    private String dzongkhagId;
    private String gewog;
    private String village;
    private String serviceTypeId;
    private String countryId;
    private String serviceSectorType;
    private String trade;
    private String email;
    private String mobileNo;
    private String telephoneNo;
    private String tpn;
    private String employeeName;
    private String employeeAddress;
    private String qualificationId;
    private String graduationYear;
    private Date graduationyr;
    private String name;
    private String universityCountry;
    private List<TradeDocument> doc;
    private String remarks;
    private String verifierremarks;
    private String approiverremarks;
    private Timestamp verifcationdate;
    private Timestamp approvaldate;
    private String updateStatus;
    private Timestamp paymentApprovedate;
    private Date paymentReceiptDate;
    private String paymentReceiptNo;
    private BigDecimal totalAmt;
    private String paymentmode;
    private Date regExpDate;
    private Date applicationDate;
    private Date initialDate;
    private Date registrationApproveDate;
    private List<TradeFeesDto> terms;
    private BigInteger noOfDaysLate;
    private BigInteger noOfDaysAfterGracePeriod;
    private BigDecimal penaltyPerDay;
    private BigDecimal paymentAmt;

    private String tradeLicenseNo;
    private String firmName;
    private String ownershiptype;
    private String pervillageserialno;
    private String regFaxNo;
    private String cmnRegisteredDzongkhagId;
    private String registeredAddress;

    private String regEmail;
    private String regMobileNo;
    private String regPhoneNo;

    private String nationality;
    private String sex;
    private String designationId;
    private String cmnOwnershipTypeId;
    private Integer showInCertificate;

    private String createdBy;
    private String verifierUser;
    private String approverUser;

    private String cancellationRemarks;

    private List<SpecializedtradeHRDTO> specializedtradeHRs;

    private List<SpecializedtradeHRAttachment> specializedtradeHRAttachments;

    private List<OwnershipDTO> ownershipDTOs;

    private List<SpecializedtradeEQ> specializedtradeEQs;

    private List<SpecializedtradeHR> specializedtradeHRList;

    private List<SpecializedtradeEQAttachment> specializedtradeEQAttachments;

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

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public BigInteger getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(BigInteger referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(String salutationId) {
        this.salutationId = salutationId;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getServiceSectorType() {
        return serviceSectorType;
    }

    public void setServiceSectorType(String serviceSectorType) {
        this.serviceSectorType = serviceSectorType;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
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

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getTpn() {
        return tpn;
    }

    public void setTpn(String tpn) {
        this.tpn = tpn;
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

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Date getGraduationyr() {
        return graduationyr;
    }

    public void setGraduationyr(Date graduationyr) {
        this.graduationyr = graduationyr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversityCountry() {
        return universityCountry;
    }

    public void setUniversityCountry(String universityCountry) {
        this.universityCountry = universityCountry;
    }

    public List<TradeDocument> getDoc() {
        return doc;
    }

    public void setDoc(List<TradeDocument> doc) {
        this.doc = doc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVerifierremarks() {
        return verifierremarks;
    }

    public void setVerifierremarks(String verifierremarks) {
        this.verifierremarks = verifierremarks;
    }

    public String getApproiverremarks() {
        return approiverremarks;
    }

    public void setApproiverremarks(String approiverremarks) {
        this.approiverremarks = approiverremarks;
    }

    public Timestamp getVerifcationdate() {
        return verifcationdate;
    }

    public void setVerifcationdate(Timestamp verifcationdate) {
        this.verifcationdate = verifcationdate;
    }

    public Timestamp getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(Timestamp approvaldate) {
        this.approvaldate = approvaldate;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Timestamp getPaymentApprovedate() {
        return paymentApprovedate;
    }

    public void setPaymentApprovedate(Timestamp paymentApprovedate) {
        this.paymentApprovedate = paymentApprovedate;
    }

    public Date getPaymentReceiptDate() {
        return paymentReceiptDate;
    }

    public void setPaymentReceiptDate(Date paymentReceiptDate) {
        this.paymentReceiptDate = paymentReceiptDate;
    }

    public String getPaymentReceiptNo() {
        return paymentReceiptNo;
    }

    public void setPaymentReceiptNo(String paymentReceiptNo) {
        this.paymentReceiptNo = paymentReceiptNo;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public Date getRegExpDate() {
        return regExpDate;
    }

    public void setRegExpDate(Date regExpDate) {
        this.regExpDate = regExpDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getRegistrationApproveDate() {
        return registrationApproveDate;
    }

    public void setRegistrationApproveDate(Date registrationApproveDate) {
        this.registrationApproveDate = registrationApproveDate;
    }

    public List<TradeFeesDto> getTerms() {
        return terms;
    }

    public void setTerms(List<TradeFeesDto> terms) {
        this.terms = terms;
    }

    public BigInteger getNoOfDaysLate() {
        return noOfDaysLate;
    }

    public void setNoOfDaysLate(BigInteger noOfDaysLate) {
        this.noOfDaysLate = noOfDaysLate;
    }

    public BigInteger getNoOfDaysAfterGracePeriod() {
        return noOfDaysAfterGracePeriod;
    }

    public void setNoOfDaysAfterGracePeriod(BigInteger noOfDaysAfterGracePeriod) {
        this.noOfDaysAfterGracePeriod = noOfDaysAfterGracePeriod;
    }

    public BigDecimal getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }

    public BigDecimal getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(BigDecimal paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public List<SpecializedtradeHRDTO> getSpecializedtradeHRs() {
        return specializedtradeHRs;
    }

    public void setSpecializedtradeHRs(List<SpecializedtradeHRDTO> specializedtradeHRs) {
        this.specializedtradeHRs = specializedtradeHRs;
    }

    public List<SpecializedtradeHRAttachment> getSpecializedtradeHRAttachments() {
        return specializedtradeHRAttachments;
    }

    public void setSpecializedtradeHRAttachments(List<SpecializedtradeHRAttachment> specializedtradeHRAttachments) {
        this.specializedtradeHRAttachments = specializedtradeHRAttachments;
    }

    public String getTradeLicenseNo() {
        return tradeLicenseNo;
    }

    public void setTradeLicenseNo(String tradeLicenseNo) {
        this.tradeLicenseNo = tradeLicenseNo;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getOwnershiptype() {
        return ownershiptype;
    }

    public void setOwnershiptype(String ownershiptype) {
        this.ownershiptype = ownershiptype;
    }

    public String getPervillageserialno() {
        return pervillageserialno;
    }

    public void setPervillageserialno(String pervillageserialno) {
        this.pervillageserialno = pervillageserialno;
    }

    public String getRegFaxNo() {
        return regFaxNo;
    }

    public void setRegFaxNo(String regFaxNo) {
        this.regFaxNo = regFaxNo;
    }

    public String getCmnRegisteredDzongkhagId() {
        return cmnRegisteredDzongkhagId;
    }

    public void setCmnRegisteredDzongkhagId(String cmnRegisteredDzongkhagId) {
        this.cmnRegisteredDzongkhagId = cmnRegisteredDzongkhagId;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public List<OwnershipDTO> getOwnershipDTOs() {
        return ownershipDTOs;
    }

    public void setOwnershipDTOs(List<OwnershipDTO> ownershipDTOs) {
        this.ownershipDTOs = ownershipDTOs;
    }

    public List<SpecializedtradeEQ> getSpecializedtradeEQs() {
        return specializedtradeEQs;
    }

    public void setSpecializedtradeEQs(List<SpecializedtradeEQ> specializedtradeEQs) {
        this.specializedtradeEQs = specializedtradeEQs;
    }

    public List<SpecializedtradeEQAttachment> getSpecializedtradeEQAttachments() {
        return specializedtradeEQAttachments;
    }

    public void setSpecializedtradeEQAttachments(List<SpecializedtradeEQAttachment> specializedtradeEQAttachments) {
        this.specializedtradeEQAttachments = specializedtradeEQAttachments;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegMobileNo() {
        return regMobileNo;
    }

    public void setRegMobileNo(String regMobileNo) {
        this.regMobileNo = regMobileNo;
    }

    public String getRegPhoneNo() {
        return regPhoneNo;
    }

    public void setRegPhoneNo(String regPhoneNo) {
        this.regPhoneNo = regPhoneNo;
    }

    public List<SpecializedtradeHR> getSpecializedtradeHRList() {
        return specializedtradeHRList;
    }

    public void setSpecializedtradeHRList(List<SpecializedtradeHR> specializedtradeHRList) {
        this.specializedtradeHRList = specializedtradeHRList;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getCmnOwnershipTypeId() {
        return cmnOwnershipTypeId;
    }

    public void setCmnOwnershipTypeId(String cmnOwnershipTypeId) {
        this.cmnOwnershipTypeId = cmnOwnershipTypeId;
    }

    public Integer getShowInCertificate() {
        return showInCertificate;
    }

    public void setShowInCertificate(Integer showInCertificate) {
        this.showInCertificate = showInCertificate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getVerifierUser() {
        return verifierUser;
    }

    public void setVerifierUser(String verifierUser) {
        this.verifierUser = verifierUser;
    }

    public String getApproverUser() {
        return approverUser;
    }

    public void setApproverUser(String approverUser) {
        this.approverUser = approverUser;
    }

    public String getCancellationRemarks() {
        return cancellationRemarks;
    }

    public void setCancellationRemarks(String cancellationRemarks) {
        this.cancellationRemarks = cancellationRemarks;
    }
}
