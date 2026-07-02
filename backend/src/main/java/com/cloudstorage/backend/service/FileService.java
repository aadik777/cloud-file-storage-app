package com.cloudstorage.backend.service;

import com.cloudstorage.backend.dto.FileResponse;
import com.cloudstorage.backend.dto.FileUploadResponse;
import com.cloudstorage.backend.entity.FileEntity;
import com.cloudstorage.backend.repository.FileRepository;
import com.cloudstorage.backend.storage.LocalFileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private LocalFileStorageService storageService;

    @Autowired
    private FileRepository fileRepository;

    // Upload File
    public FileUploadResponse uploadFile(MultipartFile file) {

        String fileName = storageService.saveFile(file);

        FileEntity fileEntity = new FileEntity();

        fileEntity.setFileName(fileName);
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileSize(file.getSize());

        fileRepository.save(fileEntity);

        return new FileUploadResponse(
                "File Uploaded Successfully",
                fileName
        );
    }

    // Get All Files
    public List<FileResponse> getAllFiles() {

        return fileRepository.findAll()
                .stream()
                .map(file -> new FileResponse(
                        file.getId(),
                        file.getFileName(),
                        file.getFileType(),
                        file.getFileSize()
                ))
                .collect(Collectors.toList());
    }

    // Download File
    public Resource downloadFile(Long id) {

        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        return storageService.downloadFile(file.getFileName());
    }

    public void deleteFile(Long id) {

    FileEntity fileEntity = fileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("File not found"));

    // delete from local storage
    storageService.deleteFile(fileEntity.getFileName());

    // delete from database
    fileRepository.delete(fileEntity);
}
}