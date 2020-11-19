package com.ngn.spring.project.cdb.trade.dto;

/**
 * Created by USER on 6/4/2020.
 */
public class OwnershipDTO {
    private String[] nationalityId;
    private String[] ownerCid;
    private String[] salutation;
    private String[] name;
    private String[] gender;
    private String[] designation;
    private Integer showInCertificate;

    private String nationality;
    private String oCid;
    private String salutationId;
    private String oname;
    private String sex;
    private String designationId;

    public String[] getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String[] nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String[] getOwnerCid() {
        return ownerCid;
    }

    public void setOwnerCid(String[] ownerCid) {
        this.ownerCid = ownerCid;
    }

    public String[] getSalutation() {
        return salutation;
    }

    public void setSalutation(String[] salutation) {
        this.salutation = salutation;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getGender() {
        return gender;
    }

    public void setGender(String[] gender) {
        this.gender = gender;
    }

    public String[] getDesignation() {
        return designation;
    }

    public void setDesignation(String[] designation) {
        this.designation = designation;
    }

    public Integer getShowInCertificate() {
        return showInCertificate;
    }

    public void setShowInCertificate(Integer showInCertificate) {
        this.showInCertificate = showInCertificate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getoCid() {
        return oCid;
    }

    public void setoCid(String oCid) {
        this.oCid = oCid;
    }

    public String getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(String salutationId) {
        this.salutationId = salutationId;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
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
}
