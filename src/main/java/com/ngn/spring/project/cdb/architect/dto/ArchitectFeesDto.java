package com.ngn.spring.project.cdb.architect.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by USER on 3/19/2020.
 */
public class ArchitectFeesDto {
    private String name;
    private Integer validaty;
    private BigInteger registrationFee;
    private BigDecimal renewalFee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValidaty() {
        return validaty;
    }

    public void setValidaty(Integer validaty) {
        this.validaty = validaty;
    }

    public BigInteger getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(BigInteger registrationFee) {
        this.registrationFee = registrationFee;
    }

    public BigDecimal getRenewalFee() {
        return renewalFee;
    }

    public void setRenewalFee(BigDecimal renewalFee) {
        this.renewalFee = renewalFee;
    }
}
