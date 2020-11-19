package com.ngn.spring.project.cdb.admin.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/10/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class AppliedServiceFeeDTO {
    private BigInteger applicationNo;
    private String serviceId;
    private Integer serviceRefNo;
    private String serviceName;
    private BigDecimal paymentAmount;
    private String firmName;
    private String appStatus;
    private Date applicationDate;

    public BigInteger getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(BigInteger applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServiceRefNo() {
        return serviceRefNo;
    }

    public void setServiceRefNo(Integer serviceRefNo) {
        this.serviceRefNo = serviceRefNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
