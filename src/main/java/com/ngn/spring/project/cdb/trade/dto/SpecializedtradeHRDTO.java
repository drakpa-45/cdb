package com.ngn.spring.project.cdb.trade.dto;

import java.util.Date;

/**
 * Created by USER on 6/9/2020.
 */
public class SpecializedtradeHRDTO {
    private String id;
    private String specializedTradeId;
    private String [] salutationId;
    private String [] cidNo;
    private String [] name;
    private String [] sex;
    private String [] countryId;
    private String [] qualificationId;
    private String [] serviceTypeId;
    private String [] tradeId;
    private String [] designationId;
    private Integer siCertificate;
    private Integer isPartnerOrOwner;
    private Date joiningDate;
    private Integer verified;
    private Integer Approved;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecializedTradeId() {
        return specializedTradeId;
    }

    public void setSpecializedTradeId(String specializedTradeId) {
        this.specializedTradeId = specializedTradeId;
    }

    public String [] getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(String[] salutationId) {
        this.salutationId = salutationId;
    }

    public String[] getCidNo() {
        return cidNo;
    }

    public void setCidNo(String[] cidNo) {
        this.cidNo = cidNo;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getSex() {
        return sex;
    }

    public void setSex(String[] sex) {
        this.sex = sex;
    }

    public String[] getCountryId() {
        return countryId;
    }

    public void setCountryId(String[] countryId) {
        this.countryId = countryId;
    }

    public String[] getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String[] qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String[] getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String[] serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String[] getTradeId() {
        return tradeId;
    }

    public void setTradeId(String[] tradeId) {
        this.tradeId = tradeId;
    }

    public String[] getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String[] designationId) {
        this.designationId = designationId;
    }

    public Integer getSiCertificate() {
        return siCertificate;
    }

    public void setSiCertificate(Integer siCertificate) {
        this.siCertificate = siCertificate;
    }

    public Integer getIsPartnerOrOwner() {
        return isPartnerOrOwner;
    }

    public void setIsPartnerOrOwner(Integer isPartnerOrOwner) {
        this.isPartnerOrOwner = isPartnerOrOwner;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getApproved() {
        return Approved;
    }

    public void setApproved(Integer approved) {
        Approved = approved;
    }
}
