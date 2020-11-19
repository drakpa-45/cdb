package com.ngn.spring.project.cdb.architect.dto;

import com.ngn.spring.project.cdb.architect.entity.ArchitectDocument;
import com.ngn.spring.project.cdb.engineer.model.EngineerAttachment;
import com.ngn.spring.project.cdb.survey.entity.SurveyDocument;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by USER on 3/23/2020.
 */
public class ArchitectDto {
    private String CrpArchitectId;
    private String CrpSurveyId;
    private String CrpEngineerId;
    private String cdbNo;
    private BigInteger ReferenceNo;
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
    private String serviceSectorTypeId;
    private String trade;
    private String cmnTradeId;
    private String email;
    private String mobileNo;
    private String employeeName;
    private String employeeAddress;
    private String qualificationId;
    private String qualification;
    private String graduationYear;
    private Date graduationyr;
    private String universityName;
    private String universityCountry;
    private List<ArchitectDocument> doc;
    private List<SurveyDocument> surveyDocuments;
    private List<EngineerAttachment> engineerAttachments;
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
    private List<ArchitectFeesDto> terms;
    private BigInteger noOfDaysLate;
    private BigInteger noOfDaysAfterGracePeriod;
    private BigDecimal penaltyPerDay;
    private BigDecimal paymentAmt;
    private String cmnServiceSectorTypeId;
    private String cmnQualificationId;
    private String cmnUniversityCountryId;
    private String cmnSalutationId;
    private String cmnDzongkhagId;
    private String cmnCountryId;
    private String createdBy;
    private String verifierUser;
    private String approverUser;
    private BigInteger refNo;
    private String cancellationRemarks;
    private String cancellationReason;

    public String getCrpArchitectId() {
        return CrpArchitectId;
    }

    public void setCrpArchitectId(String crpArchitectId) {
        CrpArchitectId = crpArchitectId;
    }

    public String getCrpSurveyId() {
        return CrpSurveyId;
    }

    public void setCrpSurveyId(String crpSurveyId) {
        CrpSurveyId = crpSurveyId;
    }

    public String getCrpEngineerId() {
        return CrpEngineerId;
    }

    public void setCrpEngineerId(String crpEngineerId) {
        CrpEngineerId = crpEngineerId;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public BigInteger getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(BigInteger referenceNo) {
        ReferenceNo = referenceNo;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
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

    public String getCmnTradeId() {
        return cmnTradeId;
    }

    public void setCmnTradeId(String cmnTradeId) {
        this.cmnTradeId = cmnTradeId;
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

    public List<ArchitectDocument> getDoc() {
        return doc;
    }

    public void setDoc(List<ArchitectDocument> doc) {
        this.doc = doc;
    }

    public List<SurveyDocument> getSurveyDocuments() {
        return surveyDocuments;
    }

    public void setSurveyDocuments(List<SurveyDocument> surveyDocuments) {
        this.surveyDocuments = surveyDocuments;
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

    public List<ArchitectFeesDto> getTerms() {
        return terms;
    }

    public void setTerms(List<ArchitectFeesDto> terms) {
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

    public String getCmnServiceSectorTypeId() {
        return cmnServiceSectorTypeId;
    }

    public void setCmnServiceSectorTypeId(String cmnServiceSectorTypeId) {
        this.cmnServiceSectorTypeId = cmnServiceSectorTypeId;
    }

    public String getCmnQualificationId() {
        return cmnQualificationId;
    }

    public void setCmnQualificationId(String cmnQualificationId) {
        this.cmnQualificationId = cmnQualificationId;
    }

    public String getCmnUniversityCountryId() {
        return cmnUniversityCountryId;
    }

    public void setCmnUniversityCountryId(String cmnUniversityCountryId) {
        this.cmnUniversityCountryId = cmnUniversityCountryId;
    }

    public String getCmnSalutationId() {
        return cmnSalutationId;
    }

    public void setCmnSalutationId(String cmnSalutationId) {
        this.cmnSalutationId = cmnSalutationId;
    }

    public String getCmnDzongkhagId() {
        return cmnDzongkhagId;
    }

    public void setCmnDzongkhagId(String cmnDzongkhagId) {
        this.cmnDzongkhagId = cmnDzongkhagId;
    }

    public String getCmnCountryId() {
        return cmnCountryId;
    }

    public void setCmnCountryId(String cmnCountryId) {
        this.cmnCountryId = cmnCountryId;
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

    public BigInteger getRefNo() {
        return refNo;
    }

    public void setRefNo(BigInteger refNo) {
        this.refNo = refNo;
    }

    public List<EngineerAttachment> getEngineerAttachments() {
        return engineerAttachments;
    }

    public void setEngineerAttachments(List<EngineerAttachment> engineerAttachments) {
        this.engineerAttachments = engineerAttachments;
    }

    public String getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(String salutationId) {
        this.salutationId = salutationId;
    }

    public String getServiceSectorTypeId() {
        return serviceSectorTypeId;
    }

    public void setServiceSectorTypeId(String serviceSectorTypeId) {
        this.serviceSectorTypeId = serviceSectorTypeId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCancellationRemarks() {
        return cancellationRemarks;
    }

    public void setCancellationRemarks(String cancellationRemarks) {
        this.cancellationRemarks = cancellationRemarks;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
