package com.ngn.spring.project.cdb.specializedFirm.model;

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpspecializedtradeappliedservice")
public class SpFirmAppliedS extends BaseModel implements Serializable {
    @Id
    //@GeneratedValue
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpSpecializedTradeId")
    private String specializedTradeId;

    @Column(name = "CmnServiceTypeId")
    private String serviceTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecializedTradeId() {
        return specializedTradeId;
    }

    public void setSpecializedTradeId(String specializedTradeId) {
        this.specializedTradeId = specializedTradeId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
}
