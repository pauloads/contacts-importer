package com.koombea.contacts.api.controller;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@ModelAttribute UploadRequest uploadRequest) {
        uploadService.handleUpload(uploadRequest);
        return ResponseEntity.ok().build();
    }
}
