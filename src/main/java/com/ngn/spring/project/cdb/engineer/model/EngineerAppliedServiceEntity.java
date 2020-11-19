package com.ngn.spring.project.cdb.engineer.model;

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by RMA on 12/25/2019.
 */

@Entity
@Table(name = "crpengineerappliedservice")
public class EngineerAppliedServiceEntity extends BaseModel {

    //region private variables.
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpEngineerId")
    private String engineerId;

    @Column(name = "CmnServiceTypeId")
    private String cmnServiceTypeId;
    //endregion


    //region getter and setters.
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getCmnServiceTypeId() {
        return cmnServiceTypeId;
    }

    public void setCmnServiceTypeId(String cmnServiceTypeId) {
        this.cmnServiceTypeId = cmnServiceTypeId;
    }
    //endregion
}
