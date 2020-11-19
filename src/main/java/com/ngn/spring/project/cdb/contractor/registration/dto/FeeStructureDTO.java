package com.ngn.spring.project.cdb.contractor.registration.dto;

import java.math.BigDecimal;

/**
 * ==================================================================================
 * Created by user on 10/6/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class FeeStructureDTO {
    private String id;
    private String name;
    private String code;
    private BigDecimal registrationFee;
    private BigDecimal renewalFee;
    private int validaty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
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

    public BigDecimal getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(BigDecimal registrationFee) {
        this.registrationFee = registrationFee;
    }

    public BigDecimal getRenewalFee() {
        return renewalFee;
    }

    public void setRenewalFee(BigDecimal renewalFee) {
        this.renewalFee = renewalFee;
    }

    public int getValidaty() {
        return validaty;
    }

    public void setValidaty(int validaty) {
        this.validaty = validaty;
    }
}
