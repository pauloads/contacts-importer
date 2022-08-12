package com.koombea.contacts.api.controller;

import com.koombea.contacts.api.dto.ContactResponse;
import com.koombea.contacts.api.mapper.ContactMapper;
import com.koombea.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    private final ContactMapper contactMapper;

    @GetMapping("/contacts")
    public ResponseEntity<Page<ContactResponse>> listAllValidContacts(Pageable pageable) {
        var contacts = contactService.listAllValidPaginated(pageable).map(contactMapper::toResponse);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/contacts/invalid")
    public ResponseEntity<Page<ContactResponse>> listAllInvalidContacts(Pageable pageable) {
        var contacts = contactService.listAllInvalidPaginated(pageable).map(contactMapper::toResponse);
        return ResponseEntity.ok(contacts);
    }
}
