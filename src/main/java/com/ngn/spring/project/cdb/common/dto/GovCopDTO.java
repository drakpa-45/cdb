package com.ngn.spring.project.cdb.common.dto;

/**
 * Created by USER on 27-Jan-21.
 */
public class GovCopDTO {

    private String positionTitle;
    private String agency;
    private String cidNo;

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }
}
