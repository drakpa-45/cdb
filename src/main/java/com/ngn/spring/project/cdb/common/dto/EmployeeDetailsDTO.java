package com.ngn.spring.project.cdb.common.dto;

/**
 * Created by USER on 03-Dec-20.
 */
public class EmployeeDetailsDTO {
    private String id;
    private String cdbNo;
    private String workId;
    private String procuringAgency;
    private String cidNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getProcuringAgency() {
        return procuringAgency;
    }

    public void setProcuringAgency(String procuringAgency) {
        this.procuringAgency = procuringAgency;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }
}
