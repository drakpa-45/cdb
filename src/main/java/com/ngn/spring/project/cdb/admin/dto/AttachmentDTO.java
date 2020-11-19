package com.ngn.spring.project.cdb.admin.dto;

/**
 * ====================================================================
 * Created by Nima Yoezer on 7/29/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class AttachmentDTO {
    private String id;
    private String refHrEqId;
    private String documentName;
    private String documentPath;
    private String fileType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefHrEqId() {
        return refHrEqId;
    }

    public void setRefHrEqId(String refHrEqId) {
        this.refHrEqId = refHrEqId;
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
