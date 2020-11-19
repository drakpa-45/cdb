package com.ngn.spring.project.cdb.admin.dto;

import java.util.Date;

/**
 * ==================================================================================
 * Created by user on 2/29/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ApplicationHistoryDTO {
    private  String contractorId;
    private String consultantId;
    private String specializedFirmId;
    private String appStatus;
    private String userId;
    private String userName;
    private Date actionDate;
    private String remarks;

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSpecializedFirmId() {
        return specializedFirmId;
    }

    public void setSpecializedFirmId(String specializedFirmId) {
        this.specializedFirmId = specializedFirmId;
    }
}
