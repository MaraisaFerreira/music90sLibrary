package com.study.mf.controllers;

import com.study.mf.data.dto.StorageResponseDTO;
import com.study.mf.services.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class StorageController {

    private final StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @GetMapping("/upload")
    public ResponseEntity<StorageResponseDTO> uploadFiles(
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
}
