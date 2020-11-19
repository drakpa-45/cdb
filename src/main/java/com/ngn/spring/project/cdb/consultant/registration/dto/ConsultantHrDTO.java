package com.ngn.spring.project.cdb.consultant.registration.dto;

import com.ngn.spring.project.cdb.admin.dto.AttachmentDTO;

import java.util.Date;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 10/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ConsultantHrDTO {
    private String id;
    private String consultantID;
    private String salutationId;
    private String cidNo;
    private String name;
    private Character sex;
    private String countryId;
    private String qualificationId;
    private String serviceTypeId;
    private String tradeId;
    private String designationId;
    private Integer siCertificate;
    private Integer isPartnerOrOwner;
    private Date joiningDate;
    private Integer verified;
    private Integer Approved;
    private Integer deleteRequest;

    private String salutationName;
    private String countryName;
    private String qualificationName;
    private String serviceTypeName;
    private String tradeName;
    private String designationName;

    List<AttachmentDTO> hrAttachments;

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

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
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

    public Integer getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(Integer deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

    public String getSalutationName() {
        return salutationName;
    }

    public void setSalutationName(String salutationName) {
        this.salutationName = salutationName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public List<AttachmentDTO> getHrAttachments() {
        return hrAttachments;
    }

    public void setHrAttachments(List<AttachmentDTO> hrAttachments) {
        this.hrAttachments = hrAttachments;
    }
}
