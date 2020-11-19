package com.ngn.spring.project.cdb.admin.dto;

import java.math.BigInteger;
import java.util.Date;

/**
 * ==================================================================================
 * Created by user on 2/13/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class TasksDTO {
    private BigInteger applicationNo;
    private String serviceNo;
    private String serviceName;
    private String firmName;
    private String contactNo;
    private String appStatus;
    private Date applicationDate;
    private String serviceSectorType;

    public BigInteger getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(BigInteger applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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

    public String getServiceSectorType() {
        return serviceSectorType;
    }

    public void setServiceSectorType(String serviceSectorType) {
        this.serviceSectorType = serviceSectorType;
    }
}
