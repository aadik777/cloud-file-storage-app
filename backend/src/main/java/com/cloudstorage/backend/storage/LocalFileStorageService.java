package com.cloudstorage.backend.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Upload File
    public String saveFile(MultipartFile file) {

        try {

            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = file.getOriginalFilename();

            File destination = new File(directory, fileName);

            System.out.println("Upload Directory: " + directory.getAbsolutePath());
            System.out.println("Destination: " + destination.getAbsolutePath());

            Files.copy(
                    file.getInputStream(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("File uploaded successfully!");

            return fileName;

        } catch (IOException e) {

            System.out.println("Upload Failed!");
            e.printStackTrace();

            throw new RuntimeException("File Upload Failed", e);

        }

    }

    // Download File
    public Resource downloadFile(String fileName) {

        try {

            Path path = Paths.get(uploadDir).resolve(fileName).normalize();

            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found");
            }

        } catch (MalformedURLException e) {

            throw new RuntimeException("File not found", e);

        }

    }

    // Delete File
    public void deleteFile(String fileName) {

        File file = new File(uploadDir, fileName);

        if (file.exists()) {
            file.delete();
        }

    }
}