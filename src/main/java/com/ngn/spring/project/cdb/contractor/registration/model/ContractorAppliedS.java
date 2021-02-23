package com.ngn.spring.project.cdb.contractor.registration.model;

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ==================================================================================
 * Created by user on 2/28/2020.
 * Description: Contractor applied service
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpcontractorappliedservice")
public class ContractorAppliedS extends BaseModel implements Serializable {
    @Id
    //@GeneratedValue
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpContractorId")
    private String contractorId;

    @Column(name = "CmnServiceTypeId")
    private String serviceTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
}
