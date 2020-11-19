package com.ngn.spring.project.commonDto;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by USER on 3/29/2020.
 */
public class TasklistDto {
    private String Application_Number;
    private int Status_Id;
    private int Service_Id;
    private String Action_Date;
    private String Assigned_User_Id;
    private int Assigned_Priv_Id;
    private BigInteger groupTaskCount;
    private BigInteger mytaskCount;
    private BigInteger registration;
    private BigInteger renewal;
    private BigInteger cancellation;
    private  String messages;
    private Date appDate;
    private String cdbNo;
    private Timestamp rejectedDate;
    private String remarks;

    public String getApplication_Number() {
        return Application_Number;
    }

    public void setApplication_Number(String application_Number) {
        Application_Number = application_Number;
    }

    public int getStatus_Id() {
        return Status_Id;
    }

    public void setStatus_Id(int status_Id) {
        Status_Id = status_Id;
    }

    public int getService_Id() {
        return Service_Id;
    }

    public void setService_Id(int service_Id) {
        Service_Id = service_Id;
    }

    public String getAction_Date() {
        return Action_Date;
    }

    public void setAction_Date(String action_Date) {
        Action_Date = action_Date;
    }

    public String getAssigned_User_Id() {
        return Assigned_User_Id;
    }

    public void setAssigned_User_Id(String assigned_User_Id) {
        Assigned_User_Id = assigned_User_Id;
    }

    public int getAssigned_Priv_Id() {
        return Assigned_Priv_Id;
    }

    public void setAssigned_Priv_Id(int assigned_Priv_Id) {
        Assigned_Priv_Id = assigned_Priv_Id;
    }

    public BigInteger getGroupTaskCount() {
        return groupTaskCount;
    }

    public void setGroupTaskCount(BigInteger groupTaskCount) {
        this.groupTaskCount = groupTaskCount;
    }

    public BigInteger getMytaskCount() {
        return mytaskCount;
    }

    public void setMytaskCount(BigInteger mytaskCount) {
        this.mytaskCount = mytaskCount;
    }

    public BigInteger getRegistration() {
        return registration;
    }

    public void setRegistration(BigInteger registration) {
        this.registration = registration;
    }

    public BigInteger getRenewal() {
        return renewal;
    }

    public void setRenewal(BigInteger renewal) {
        this.renewal = renewal;
    }

    public BigInteger getCancellation() {
        return cancellation;
    }

    public void setCancellation(BigInteger cancellation) {
        this.cancellation = cancellation;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public Timestamp getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Timestamp rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
