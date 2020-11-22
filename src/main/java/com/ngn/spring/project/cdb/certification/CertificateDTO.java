package com.ngn.spring.project.cdb.certification;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by USER on 3/23/2020.
 */
public class CertificateDTO {
    private String CrpArchitectId;
    private String CrpSurveyId;
    private String CrpEngineerId;
    private String cdbNo;
    private BigInteger referenceNo;
    private String salutation;
    private String salutationId;
    private String ownerCID;
    private String ownerName;
    private String dzongkhagId;
    private String dzongkhagName;
    private String countryName;
    private String countryId;
    private String firmName;
    private String serviceTypeId;
    private String serviceSectorType;
    private String serviceSectorTypeId;
    private String qualificationId;
    private String qualification;
    private String regExpiryDate;
    private String revalidationDate;
    private String initialRegistrationDate;

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

    public String getOwnerCID() {
        return ownerCID;
    }

    public void setOwnerCID(String ownerCID) {
        this.ownerCID = ownerCID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDzongkhagId() {
        return dzongkhagId;
    }

    public void setDzongkhagId(String dzongkhagId) {
        this.dzongkhagId = dzongkhagId;
    }

    public String getDzongkhagName() {
        return dzongkhagName;
    }

    public void setDzongkhagName(String dzongkhagName) {
        this.dzongkhagName = dzongkhagName;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceSectorType() {
        return serviceSectorType;
    }

    public void setServiceSectorType(String serviceSectorType) {
        this.serviceSectorType = serviceSectorType;
    }

    public String getServiceSectorTypeId() {
        return serviceSectorTypeId;
    }

    public void setServiceSectorTypeId(String serviceSectorTypeId) {
        this.serviceSectorTypeId = serviceSectorTypeId;
    }

    public String getRegExpiryDate() {
        return regExpiryDate;
    }

    public void setRegExpiryDate(String regExpiryDate) {
        this.regExpiryDate = regExpiryDate;
    }

    public String getRevalidationDate() {
        return revalidationDate;
    }

    public void setRevalidationDate(String revalidationDate) {
        this.revalidationDate = revalidationDate;
    }

    public String getInitialRegistrationDate() {
        return initialRegistrationDate;
    }

    public void setInitialRegistrationDate(String initialRegistrationDate) {
        this.initialRegistrationDate = initialRegistrationDate;
    }
}
