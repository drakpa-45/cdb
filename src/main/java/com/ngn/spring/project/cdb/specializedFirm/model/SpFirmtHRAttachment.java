package com.ngn.spring.project.cdb.specializedFirm.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpspecializedtradehumanresourceattachment")
public class SpFirmtHRAttachment extends BaseModel implements Serializable {
    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpSpecializedtradeHumanResourceId")
    private String specializedHrId;
    @Column(name = "DocumentName")
    private String documentName;
    @Column(name = "DocumentPath")
    private String documentPath;
    @Column(name = "FileType")
    private String fileType;

    @Transient
    private String cidNo;

    @Transient
    private MultipartFile attachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecializedHrId() {
        return specializedHrId;
    }

    public void setSpecializedHrId(String specializedHrId) {
        this.specializedHrId = specializedHrId;
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

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }
}
