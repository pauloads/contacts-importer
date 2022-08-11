package com.koombea.contacts.service;

import com.koombea.contacts.exception.InvalidFieldException;
import com.koombea.contacts.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactValidatorService {

    private static final Pattern PHONE_PATTERN = Pattern.compile("\\(\\+\\d{2}\\)\\s\\d{3}((\\-)|(\\s))\\d{3}((\\-)|(\\s))\\d{2}((\\-)|(\\s))\\d{2}(($)|(\\s\\d{2}))");
    private static final Pattern NAME_PATTERN = Pattern.compile("^([A-Z-a-z-\\s]+)$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(".*");
    private static final Pattern DATE_OF_BIRTH_PATTERN = Pattern.compile("(\\d{8})|(\\d{4}-\\d{2}-\\d{2})");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");

    public void validateContact(Contact contact) {
        validate("phone", contact.getPhone(), PHONE_PATTERN);
        validate("name", contact.getName(), NAME_PATTERN);
        validate("dateOfBirth", contact.getDateOfBirth(), DATE_OF_BIRTH_PATTERN);
        validate("email", contact.getEmail(), EMAIL_PATTERN);
    }

    private void validate(String fieldName, String fieldValue, Pattern pattern) {
        Matcher matcher = pattern.matcher(fieldValue);
        if (!matcher.matches()) {
            throw new InvalidFieldException(String.format("%s is invalid: %s", fieldName, fieldValue));
        }
    }
}
