package com.koombea.contacts.service;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.exception.InvalidFieldException;
import com.koombea.contacts.exception.UnsupportedCreditCardNetworkException;
import com.koombea.contacts.model.Contact;
import com.koombea.contacts.model.Upload;
import com.koombea.contacts.model.UploadStatus;
import com.koombea.contacts.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.koombea.contacts.util.RegexUtil.containsOnlyNumbers;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepository uploadRepository;

    private final UserService userService;

    public Upload saveUploadFinishedInfo(Upload upload) {
        upload.setStatus(UploadStatus.FINISHED);
        upload.setFinishedAt(LocalDateTime.now());
        return uploadRepository.save(upload);
    }

    public Upload saveUploadFailedInfo(Upload upload, String message) {
        upload.setStatus(UploadStatus.FAILED);
        upload.setFailureReason(message);
        upload.setFinishedAt(LocalDateTime.now());
        return uploadRepository.save(upload);
    }

    public Upload saveUploadStartedInfo(UploadRequest uploadRequest) {
        var upload = new Upload();
        upload.setStartedAt(LocalDateTime.now());
        upload.setFileName(uploadRequest.getFile().getOriginalFilename());
        upload.setStatus(UploadStatus.PROCESSING);
        upload.setUser(userService.getAuthenticatedUser());
        return uploadRepository.save(upload);
    }

    public Page<Upload> listAllPaginated(Pageable pageable){
        var authenticatedUser = userService.getAuthenticatedUser();
        return uploadRepository.findAllByUser(authenticatedUser, pageable);
    }

}
