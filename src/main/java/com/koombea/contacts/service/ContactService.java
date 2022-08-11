package com.koombea.contacts.service;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.model.Contact;
import com.koombea.contacts.repository.ContactRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> saveContacts(List<Contact> contacts) {
        return contactRepository.saveAll(contacts);
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
}
