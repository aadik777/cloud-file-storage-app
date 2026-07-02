package com.cloudstorage.backend.dto;

public class FileUploadResponse {

    private String message;
    private String fileName;

    public FileUploadResponse() {
    }

    public FileUploadResponse(String message, String fileName) {
        this.message = message;
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public String getFileName() {
        return fileName;
    }
}