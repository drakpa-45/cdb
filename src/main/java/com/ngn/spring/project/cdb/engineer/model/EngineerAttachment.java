package com.ngn.spring.project.cdb.engineer.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by RMA on 12/25/2019.
 */

@Entity
@Table(name = "crpengineerattachment")
public class EngineerAttachment extends BaseModel {

    @Id
    @Column(name="Id")
    private String id;

    @Column(name="CrpEngineerId")
    private String engineerId;

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

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
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
