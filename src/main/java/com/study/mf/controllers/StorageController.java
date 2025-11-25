package com.study.mf.controllers;

import com.study.mf.data.dto.StorageResponseDTO;
import com.study.mf.services.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/files")
public class StorageController {

    private final StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<StorageResponseDTO> uploadFile(
        @RequestParam(name = "file") MultipartFile file
    ) {
        if (file == null) {
            throw new RuntimeException("File cannot be null...");
        }

        String fileName = service.uploadFile(file);

        return ResponseEntity.ok(new StorageResponseDTO(
            fileName,
            file.getSize(),
            file.getContentType()
        ));
    }

    @PostMapping("/uploadFiles")
    public ResponseEntity<List<StorageResponseDTO>> uploadFiles(
        @RequestParam(name = "files") MultipartFile[] files
    ) {
        List<StorageResponseDTO> dtos = Arrays.stream(files).map(
            file -> {
                String fileName = service.uploadFile(file);
                Long fileSize = file.getSize();
                String fileType = file.getContentType();
                return new StorageResponseDTO(fileName, fileSize, fileType);
            }).toList();

        return ResponseEntity.status(201).body(dtos);
    }
}

