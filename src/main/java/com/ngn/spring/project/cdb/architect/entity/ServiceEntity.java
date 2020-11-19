package com.ngn.spring.project.cdb.architect.entity;

/**
 * Created by USER on 3/23/2020.
 */

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "crparchitectappliedservice")
public class ServiceEntity extends BaseModel {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpArchitectId")
    private String architectId;

    @Column(name = "CmnServiceTypeId")
    private String cmnServiceTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArchitectId() {
        return architectId;
    }

    public void setArchitectId(String architectId) {
        this.architectId = architectId;
    }

    public String getCmnServiceTypeId() {
        return cmnServiceTypeId;
    }

    public void setCmnServiceTypeId(String cmnServiceTypeId) {
        this.cmnServiceTypeId = cmnServiceTypeId;
    }
}
