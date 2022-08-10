package com.koombea.contacts.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsController {

    @GetMapping("/contacts")
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }
}
