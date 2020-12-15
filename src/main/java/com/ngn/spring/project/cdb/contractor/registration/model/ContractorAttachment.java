package com.ngn.spring.project.cdb.contractor.registration.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ==================================================================================
 * Created by user on 3/3/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Entity
@Table(name = "crpcontractorattachment")
public class ContractorAttachment extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpContractorId")
    private String contractorId;
    @Column(name = "DocumentName")
    private String documentName;
    @Column(name = "DocumentPath")
    private String documentPath;
    @Column(name = "AttachmentFor")
    private String attachmentFor;
    @Column(name = "FileType")
    private String fileType;


    @Transient
    private MultipartFile attachment;

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

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentFor() {
        return attachmentFor;
    }

    public void setAttachmentFor(String attachmentFor) {
        this.attachmentFor = attachmentFor;
    }
}
