package com.ngn.spring.project.cdb.consultant.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpconsultanthumanresource")
public class  ConsultantHR  extends BaseModel implements Serializable {
    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpConsultantId")
    private String consultantID;
    @Column(name = "CmnSalutationId")
    private String salutationId;
    @Column(name = "CIDNo")
    private String cidNo;
    @Column(name = "Name")
    private String name;
    @Column(name = "Sex")
    private String sex;
    @Column(name = "CmnCountryId")
    private String countryId;
    @Column(name = "CmnQualificationId")
    private String qualificationId;
    @Column(name = "CmnServiceTypeId")
    private String serviceTypeId;
    @Column(name = "CmnTradeId")
    private String tradeId;
    @Column(name = "CmnDesignationId")
    private String designationId;
    @Column(name = "ShowInCertificate")
    private Integer siCertificate;
    @Column(name = "IsPartnerOrOwner")
    private Integer isPartnerOrOwner;
    @Column(name = "JoiningDate")
    private Date joiningDate;
    @Column(name = "DeleteRequest")
    private String deleteRequest;
    @Column(name = "Verified")
    private Integer verified;
    @Column(name = "Approved")
    private Integer Approved;

    @Transient
    private List<ConsultantHRAttachment> consultantHRAs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsultantID() {
        return consultantID;
    }

    public void setConsultantID(String consultantID) {
        this.consultantID = consultantID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
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

    public List<ConsultantHRAttachment> getConsultantHRAs() {
        return consultantHRAs;
    }

    public void setConsultantHRAs(List<ConsultantHRAttachment> consultantHRAs) {
        this.consultantHRAs = consultantHRAs;
    }

    public String getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(String deleteRequest) {
        this.deleteRequest = deleteRequest;
    }
}
