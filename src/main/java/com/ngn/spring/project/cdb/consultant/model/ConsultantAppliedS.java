package com.ngn.spring.project.cdb.consultant.model;

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
@Table(name = "crpconsultantappliedservice")
public class ConsultantAppliedS extends BaseModel implements Serializable {
    @Id
    //@GeneratedValue
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpConsultantId")
    private String consultantId;

    @Column(name = "CmnServiceTypeId")
    private String serviceTypeId;

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

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
}
