package com.koombea.contacts.service;

import com.koombea.contacts.exception.InvalidFieldException;
import com.koombea.contacts.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ContactValidatorServiceTest {

    @InjectMocks
    private ContactValidatorService contactValidatorService;

    private Contact getValidContact() {
        var contact = new Contact();
        contact.setPhone("(+00) 000 000 00 00 00");
        contact.setDateOfBirth("1981-08-17");
        contact.setEmail("lorem.ipsum.sodales@aol.couk");
        contact.setName("Arnold Schwarzenegger");
        return contact;
    }

    @Test
    public void shouldThrowExceptionWhenNameIsInvalid() {
        var contact = getValidContact();
        contact.setName("Paulo Corrêa");

        InvalidFieldException exception = Assertions.assertThrows(InvalidFieldException.class, () -> {
            contactValidatorService.validateContact(contact);
        });
    }

    @Test
    public void shouldThrowExceptionWhenPhoneIsInvalid() {
        var contact = getValidContact();
        contact.setPhone("+00 (00) 0 0000-0000");

        InvalidFieldException exception = Assertions.assertThrows(InvalidFieldException.class, () -> {
            contactValidatorService.validateContact(contact);
        });
    }

    @Test
    public void shouldThrowExceptionWhenDateOfBirthIsInvalid() {
        var contact = getValidContact();
        contact.setDateOfBirth("12-12-2012");

        InvalidFieldException exception = Assertions.assertThrows(InvalidFieldException.class, () -> {
            contactValidatorService.validateContact(contact);
        });
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsInvalid() {
        var contact = getValidContact();
        contact.setEmail("Arnold.Schwarzenegger.com");

        InvalidFieldException exception = Assertions.assertThrows(InvalidFieldException.class, () -> {
            contactValidatorService.validateContact(contact);
        });
    }

}