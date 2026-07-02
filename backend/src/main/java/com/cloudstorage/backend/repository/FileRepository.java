package com.cloudstorage.backend.repository;

import com.cloudstorage.backend.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}