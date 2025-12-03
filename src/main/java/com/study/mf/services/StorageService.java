package com.study.mf.services;

import com.study.mf.config.StorageConfig;
import com.study.mf.exceptions.CustomNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class StorageService {

    private final Path storageFolderPath;

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    public StorageService(StorageConfig config) {
        storageFolderPath = Path.of(config.getStorageFolderStr()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(storageFolderPath);
        } catch (IOException e) {
            logger.warn("Storage Folder cannot be created...");
        }
    }

    public String uploadFile(MultipartFile file){
        String fileName = file.getOriginalFilename();

        Path filePath = storageFolderPath.resolve(Objects.requireNonNull(fileName));

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Copy Failed...");
        }
    }

    public Resource loadResourceFile(String fileName){
        Path filePath = storageFolderPath.resolve(fileName);

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new CustomNotFoundException("This file was not found...");
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
