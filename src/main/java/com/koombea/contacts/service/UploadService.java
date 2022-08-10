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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {

    private final ContactValidatorService contactValidatorService;

    private final UploadRepository uploadRepository;

    private final UserService userService;

    private final CreditCardProcessorService creditCardProcessorService;

    public void handleUpload(UploadRequest uploadRequest) {
        var upload = saveUploadStartInfo(uploadRequest);
        List<Contact> contacts = extractRecordsFromFile(uploadRequest);
        try {
            contactValidatorService.validateContacts(contacts);
            obtainCreditCardNetwork(contacts);
            saveUploadFinishedInfo(upload);
        } catch (InvalidFieldException ex) {
            log.error("error during field validation: {}", ex.getMessage());
            saveUploadFailedInfo(upload, ex);
        }
    }

    private void saveUploadFinishedInfo(Upload upload) {
        upload.setStatus(UploadStatus.FINISHED);
        upload.setFinishedAt(LocalDateTime.now());
        uploadRepository.save(upload);
    }

    private void saveUploadFailedInfo(Upload upload, InvalidFieldException ex) {
        upload.setStatus(UploadStatus.FAILED);
        upload.setFailureReason(ex.getMessage());
        upload.setFinishedAt(LocalDateTime.now());
        uploadRepository.save(upload);
    }

    private Upload saveUploadStartInfo(UploadRequest uploadRequest) {
        var upload = new Upload();
        upload.setStartedAt(LocalDateTime.now());
        upload.setFileName(uploadRequest.getFile().getName());
        upload.setStatus(UploadStatus.PROCESSING);
        upload.setUser(userService.getAuthenticatedUser());
        return uploadRepository.save(upload);
    }

    public List<Contact> extractRecordsFromFile(UploadRequest upload) {
        try {
            Reader reader = new InputStreamReader(upload.getFile().getInputStream());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            List<Contact> contacts = new ArrayList<>();
            for (CSVRecord record : records) {
                var contact = new Contact();
                contact.setAddress(record.get(upload.getAddress()));
                contact.setCreditCardNumber(record.get(upload.getCreditCardNumber()));
                contact.setEmail(record.get(upload.getEmail()));
                contact.setPhone(record.get(upload.getPhone()));
                contact.setName(record.get(upload.getName()));
                contact.setDateOfBirth(record.get(upload.getDateOfBirth()));

                contacts.add(contact);
            }
            return contacts;
        } catch (IOException ex) {
            log.error("error extracting records from file");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error extracting records from file");
        }
    }

    public void obtainCreditCardNetwork(List<Contact> contacts) {
        contacts.stream().forEach(contact -> {
            try {
                contact.setCreditCardNetwork(creditCardProcessorService
                        .obtainCreditCardNetwork(contact.getCreditCardNumber()).name());
            } catch (UnsupportedCreditCardNetworkException ex) {
                log.error(ex.getMessage());
                contact.setCreditCardNetwork("UNSUPPORTED"); //TODO: improve
            }
        });
    }
}
