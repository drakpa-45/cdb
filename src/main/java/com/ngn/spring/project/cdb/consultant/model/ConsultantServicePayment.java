package com.ngn.spring.project.cdb.consultant.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ==================================================================================
 * Created by user on 3/13/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpconsultantservicepayment")
public class ConsultantServicePayment extends BaseModel implements Serializable {
    /*
    * INSERT INTO `cdb_local`.`crpcontractorservicepayment`
        (`Id`,
        `CrpContractorId`,
        `CmnServiceTypeId`,
        `NoOfDaysLate`,
        `NoOfDaysAfterGracePeriod`,
        `PenaltyPerDay`,
        `TotalAmount`,
        `PaymentAmount`,
        `WaiveOffLateFee`,
        `NewLateFeeAmount`,
        `CreatedBy`,
        `EditedBy`,
        `CreatedOn`,
        `EditedOn`
        )
    * */
    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpConsultantId")
    private String consultantId;
    @Column(name = "CmnServiceTypeId")
    private String cmnServiceTypeId;
    @Column(name = "NoOfDaysLate")
    private Integer noOfDaysLate;
    @Column(name = "NoOfDaysAfterGracePeriod")
    private Integer noOfDaysAfterGracePeriod;
    @Column(name = "PenaltyPerDay")
    private BigDecimal penaltyPerDay;
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;
    @Column(name = "PaymentAmount")
    private BigDecimal paymentAmount;
    @Column(name = "WaiveOffLateFee")
    private BigDecimal waiveOffLateFee;
    @Column(name = "NewLateFeeAmount")
    private BigDecimal newLateFeeAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getCmnServiceTypeId() {
        return cmnServiceTypeId;
    }

    public void setCmnServiceTypeId(String cmnServiceTypeId) {
        this.cmnServiceTypeId = cmnServiceTypeId;
    }

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
