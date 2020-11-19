package com.ngn.spring.project.cdb.contractor.cc;

/**
 * ====================================================================
 * Created by Nima Yoezer on 9/6/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class CancellationDTO {
    private String cancellationReason;
    private Character cancelConfirm;
    private String cdbNo;

    public Character getCancelConfirm() {
        return cancelConfirm;
    }

    public void setCancelConfirm(Character cancelConfirm) {
        this.cancelConfirm = cancelConfirm;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }
}
