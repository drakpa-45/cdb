package com.ngn.spring.project.lib;

import java.util.Date;

/**
 * ====================================================================
 * Created by DELL on 28/05/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public class ResponseMessage {
    private Integer status;
    private String text;
    private Object dto;
    private Object dto1;
    private String value;
    private String val2;

    // Data for Approval Request
    private String cmdFlag;
    private String screenId;
    private String userId;
    private Date systemOpenDate;
    private String requestId;
    private String responseText;
    private String id;

    //region Setter Declaration

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }

    public Object getDto1() {
        return dto1;
    }

    public void setDto1(Object dto1) {
        this.dto1 = dto1;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCmdFlag() {
        return cmdFlag;
    }

    public void setCmdFlag(String cmdFlag) {
        this.cmdFlag = cmdFlag;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSystemOpenDate() {
        return systemOpenDate;
    }

    public void setSystemOpenDate(Date systemOpenDate) {
        this.systemOpenDate = systemOpenDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void reset(){
        this.status = null;
        this.text = null;
        this.dto = null;
        this.value = null;
        this.cmdFlag = null;
        this.screenId = null;
        this.userId = null;
        this.systemOpenDate = null;
        this.requestId = null;
        this.responseText = null;
        this.val2 = null;
        this.dto1 = null;
        this.id = null;
    }


}
