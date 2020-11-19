package com.ngn.spring.project.cdb.survey.entity;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by USER on 3/23/2020.
 */
@Entity
@Table(name="crpsurveyattachment")
public class SurveyDocument extends BaseModel {
    @Id
    @Column(name="Id")
    private String id;

    @Column(name="CrpSurveyId")
    private String surveyid;

    @Column(name="DocumentName")
    private String documentName;

    @NotNull
    @Column(name="DocumentPath")
    private String documentPath;

    @NotNull
    @Column(name="FileType")
    private String fileType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(String surveyid) {
        this.surveyid = surveyid;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
