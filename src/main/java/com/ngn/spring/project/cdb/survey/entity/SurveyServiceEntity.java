package com.ngn.spring.project.cdb.survey.entity;

/**
 * Created by USER on 3/23/2020.
 */

import com.ngn.spring.project.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "crpsurveyappliedservice")
public class SurveyServiceEntity extends BaseModel {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "CrpSurveyId")
    private String surveyId;

    @Column(name = "CmnServiceTypeId")
    private String cmnServiceTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getCmnServiceTypeId() {
        return cmnServiceTypeId;
    }

    public void setCmnServiceTypeId(String cmnServiceTypeId) {
        this.cmnServiceTypeId = cmnServiceTypeId;
    }
}
