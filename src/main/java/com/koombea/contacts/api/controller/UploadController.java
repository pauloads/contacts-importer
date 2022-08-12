package com.koombea.contacts.api.controller;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.api.dto.UploadResponse;
import com.koombea.contacts.api.mapper.UploadMapper;
import com.koombea.contacts.service.UploadService;
import com.koombea.contacts.service.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final Facade facade;

    private final UploadMapper uploadMapper;

    private final UploadService uploadService;

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

    @GetMapping("/upload")
    public ResponseEntity<Page<UploadResponse>> listAllUploads(Pageable pageable) {
        var uploads = uploadService.listAllPaginated(pageable).map(uploadMapper::toResponse);
        return ResponseEntity.ok(uploads);
    }
}
