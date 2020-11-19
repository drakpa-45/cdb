package com.ngn.spring.project.cdb.consultant.model;

import com.ngn.spring.project.base.BaseModel;
import com.sun.istack.internal.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 4/20/2020.
 */
@Entity
@Table(name = "crpconsultantequipmentattachment")
public class ConsultantEQAttachment extends BaseModel implements Serializable {

    @Id
    @NotNull
    @Column(name = "Id")
    private String id;
    @Column(name = "CrpConsultantEquipmentId")
    private String equipmentId;
    @Column(name = "DocumentName")
    private String documentName;
    @Column(name = "DocumentPath")
    private String documentPath;
    @Column(name = "FileType")
    private String fileType;

    @Transient
    private MultipartFile attachment;

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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
