package com.ngn.spring.project.cdb.contractor.renewal;

import java.math.BigDecimal;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/3/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class LateFeeDTO {
    private Integer noOfDaysLate;
    private Integer noOfDaysAfterGracePeriod;
    private BigDecimal penaltyPerDay;
    private BigDecimal totalAmount;
    private BigDecimal paymentAmount;
    private BigDecimal waiveOffLateFee;
    private BigDecimal newLateFeeAmount;

    public Integer getNoOfDaysLate() {
        return noOfDaysLate;
    }

    public void setNoOfDaysLate(Integer noOfDaysLate) {
        this.noOfDaysLate = noOfDaysLate;
    }

    public Integer getNoOfDaysAfterGracePeriod() {
        return noOfDaysAfterGracePeriod;
    }

    public void setNoOfDaysAfterGracePeriod(Integer noOfDaysAfterGracePeriod) {
        this.noOfDaysAfterGracePeriod = noOfDaysAfterGracePeriod;
    }

    public BigDecimal getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getWaiveOffLateFee() {
        return waiveOffLateFee;
    }

    public void setWaiveOffLateFee(BigDecimal waiveOffLateFee) {
        this.waiveOffLateFee = waiveOffLateFee;
    }

    public BigDecimal getNewLateFeeAmount() {
        return newLateFeeAmount;
    }

    public void setNewLateFeeAmount(BigDecimal newLateFeeAmount) {
        this.newLateFeeAmount = newLateFeeAmount;
    }
}
