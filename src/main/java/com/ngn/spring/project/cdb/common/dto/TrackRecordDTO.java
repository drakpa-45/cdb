package com.ngn.spring.project.cdb.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ====================================================================
 * Created by Nima Yoezer on 10/29/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class TrackRecordDTO {
    /**
     * SELECT 	`Year1` years,
     `CDBNo` cdbNo,
     `firmName` firmName,
     `PAgency` pAgency,
     `Nworks` nWorks,
     `WClassification` wClassification,
     `BidAmount` bidAmount,
     `EvalAmount` evalAmount,
     `Dzongkhag` dzongkhag,
     `Status` status,
     `Startdt` startDate,
     `CompleDt` completeDate,
     `Rating` rating,
     `Scoring` scoring,
     `Recordid` recordId,
     `WorkId` workId,
     `EvaluationOntime` evaluationOnTime,
     `EvaluationQuality` evaluationQty,
     `Remarks` remarks
     FROM
     trackrecord WHERE CDBNo ='3481' ORDER BY Year1 desc
     */
    private Integer years;
    private String cdbNo;
    private String firmName;
    private String pAgency;
    private String wClassification;
    private BigDecimal bidAmount;
    private BigDecimal evalAmount;
    private String dzongkhag;
    private String status;
    private String nWorks;
    private Date startDate;
    private Date completeDate;
    private String rating;
    private BigDecimal scoring;
    private String recordId;
    private String workId;
    private BigDecimal evaluationOnTime;
    private BigDecimal evaluationQty;
    private String remarks;

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getpAgency() {
        return pAgency;
    }

    public void setpAgency(String pAgency) {
        this.pAgency = pAgency;
    }

    public String getwClassification() {
        return wClassification;
    }

    public void setwClassification(String wClassification) {
        this.wClassification = wClassification;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public BigDecimal getEvalAmount() {
        return evalAmount;
    }

    public void setEvalAmount(BigDecimal evalAmount) {
        this.evalAmount = evalAmount;
    }

    public String getDzongkhag() {
        return dzongkhag;
    }

    public void setDzongkhag(String dzongkhag) {
        this.dzongkhag = dzongkhag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getnWorks() {
        return nWorks;
    }

    public void setnWorks(String nWorks) {
        this.nWorks = nWorks;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public BigDecimal getScoring() {
        return scoring;
    }

    public void setScoring(BigDecimal scoring) {
        this.scoring = scoring;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public BigDecimal getEvaluationOnTime() {
        return evaluationOnTime;
    }

    public void setEvaluationOnTime(BigDecimal evaluationOnTime) {
        this.evaluationOnTime = evaluationOnTime;
    }

    public BigDecimal getEvaluationQty() {
        return evaluationQty;
    }

    public void setEvaluationQty(BigDecimal evaluationQty) {
        this.evaluationQty = evaluationQty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }
}
