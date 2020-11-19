package com.ngn.spring.project.cdb.consultant.registration.dto;

import java.util.Date;

/**
 * ==================================================================================
 * Created by user on 3/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ConsultantTrainingDTO {
    private String cidNo;
    private String tTypeId;
    private String tModuleId;
    private Date fromDate;
    private Date toDate;
    private String participant;

    private String tType;
    private String tModule;

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String gettTypeId() {
        return tTypeId;
    }

    public void settTypeId(String tTypeId) {
        this.tTypeId = tTypeId;
    }

    public String gettModuleId() {
        return tModuleId;
    }

    public void settModuleId(String tModuleId) {
        this.tModuleId = tModuleId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public String gettModule() {
        return tModule;
    }

    public void settModule(String tModule) {
        this.tModule = tModule;
    }
}
