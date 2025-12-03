package com.study.mf.controllers;

import com.study.mf.data.dto.StorageResponseDTO;
import com.study.mf.exceptions.CustomBadRequestException;
import com.study.mf.services.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        if (file.isEmpty()) {
            throw new RuntimeException("You don't send any file to upload...");
        }

        String fileName = service.uploadFile(file);

        return ResponseEntity.status(201).body(new StorageResponseDTO(
            fileName,
            file.getSize(),
            file.getContentType()
        ));
    }

    @PostMapping("/uploadFiles")
    public ResponseEntity<List<StorageResponseDTO>> uploadFiles(
        @RequestParam(name = "files") MultipartFile[] files
    ) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw new CustomBadRequestException("There is any file to upload....");
            }
        }

        List<StorageResponseDTO> storageResponseDTOList = Arrays.stream(files).map(
            file -> {
                String fileName = service.uploadFile(file);
                Long fileSize = file.getSize();
                String fileType = file.getContentType();
                return new StorageResponseDTO(fileName, fileSize, fileType);
            }).toList();

        return ResponseEntity.status(201).body(storageResponseDTOList);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = service.loadResourceFile(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(
                resource.getFile().getAbsolutePath()
            );
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + resource.getFilename() + "\"")
            .body(resource);
    }
}

