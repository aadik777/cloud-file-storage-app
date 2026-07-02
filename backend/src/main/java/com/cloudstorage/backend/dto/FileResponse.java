package com.cloudstorage.backend.dto;

public class FileResponse {

    private Long id;
    private String fileName;
    private String fileType;
    private Long fileSize;

    public FileResponse() {
    }

    public FileResponse(Long id, String fileName, String fileType, Long fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }
}