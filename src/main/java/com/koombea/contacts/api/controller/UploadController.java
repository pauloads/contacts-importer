package com.koombea.contacts.api.controller;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.api.mapper.UploadMapper;
import com.koombea.contacts.service.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final Facade facade;

    private final UploadMapper uploadMapper;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@ModelAttribute UploadRequest uploadRequest) {
        var upload = facade.processUpload(uploadRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(upload.getId())
                .toUri();
        return ResponseEntity.created(location).body(uploadMapper.toResponse(upload));
    }
}
