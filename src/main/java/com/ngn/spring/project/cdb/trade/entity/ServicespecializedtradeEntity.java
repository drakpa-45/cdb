package com.ngn.spring.project.cdb.trade.entity;

/**
 * Created by USER on 3/23/2020.
 */

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "crpspecializedtradeappliedservice")
public class ServicespecializedtradeEntity extends BaseModel {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpSpecializedTradeId")
    private String crpSpecializedTradeId;

    @Column(name = "CmnServiceTypeId")
    private String cmnServiceTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrpSpecializedTradeId() {
        return crpSpecializedTradeId;
    }

    public void setCrpSpecializedTradeId(String crpSpecializedTradeId) {
        this.crpSpecializedTradeId = crpSpecializedTradeId;
    }

    public String getCmnServiceTypeId() {
        return cmnServiceTypeId;
    }

    public void setCmnServiceTypeId(String cmnServiceTypeId) {
        this.cmnServiceTypeId = cmnServiceTypeId;
    }
}
