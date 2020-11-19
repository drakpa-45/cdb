package com.ngn.spring.project.cdb.common.dto;

/**
 * ==================================================================================
 * Created by user on 3/9/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class FileDetailDTO {
    private String fileName;
    private String filePath;
    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
