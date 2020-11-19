package com.ngn.spring.project.cdb.admin.dto;

import java.math.BigDecimal;

/**
 * ==================================================================================
 * Created by user on 2/19/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class CategoryClassDTO {
    private String id;
    private String contractorId;
    private String categoryId;
    private String aClassId;
    private String vClassId;
    private String apClassId;
    private String exClassId;

    private String categoryName;
    private String aClassName;
    private String vClassName;
    private String apClassName;
    private String exClassName;

    private String consultantId;
    private String serviceId;
    private String name;
    private String code;
    private String text;
    private String obj1;
    private String value;

    private BigDecimal aAmount;
    private BigDecimal vAmount;
    private BigDecimal apAmount;

    private String serviceName;
    private Integer serviceRefNo;

    private String specializedFirmId;

    public CategoryClassDTO(){}

    public CategoryClassDTO(String categoryId, String aClassId) {
        this.categoryId = categoryId;
        this.aClassId = aClassId;
    }

    public CategoryClassDTO(String categoryId, String aClassId,String exClassId) {
        this.categoryId = categoryId;
        this.aClassId = aClassId;
        this.exClassId = exClassId;
    }

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getaClassId() {
        return aClassId;
    }

    public void setaClassId(String aClassId) {
        this.aClassId = aClassId;
    }

    public String getvClassId() {
        return vClassId;
    }

    public void setvClassId(String vClassId) {
        this.vClassId = vClassId;
    }

    public String getApClassId() {
        return apClassId;
    }

    public void setApClassId(String apClassId) {
        this.apClassId = apClassId;
    }

    public String getExClassId() {
        return exClassId;
    }

    public void setExClassId(String exClassId) {
        this.exClassId = exClassId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getaClassName() {
        return aClassName;
    }

    public void setaClassName(String aClassName) {
        this.aClassName = aClassName;
    }

    public String getvClassName() {
        return vClassName;
    }

    public void setvClassName(String vClassName) {
        this.vClassName = vClassName;
    }

    public String getApClassName() {
        return apClassName;
    }

    public void setApClassName(String apClassName) {
        this.apClassName = apClassName;
    }

    public String getExClassName() {
        return exClassName;
    }

    public void setExClassName(String exClassName) {
        this.exClassName = exClassName;
    }

    public BigDecimal getaAmount() {
        return aAmount;
    }

    public void setaAmount(BigDecimal aAmount) {
        this.aAmount = aAmount;
    }

    public BigDecimal getvAmount() {
        return vAmount;
    }

    public void setvAmount(BigDecimal vAmount) {
        this.vAmount = vAmount;
    }

    public BigDecimal getApAmount() {
        return apAmount;
    }

    public void setApAmount(BigDecimal apAmount) {
        this.apAmount = apAmount;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getObj1() {
        return obj1;
    }

    public void setObj1(String obj1) {
        this.obj1 = obj1;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSpecializedFirmId() {
        return specializedFirmId;
    }

    public void setSpecializedFirmId(String specializedFirmId) {
        this.specializedFirmId = specializedFirmId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceRefNo() {
        return serviceRefNo;
    }

    public void setServiceRefNo(Integer serviceRefNo) {
        this.serviceRefNo = serviceRefNo;
    }
}
