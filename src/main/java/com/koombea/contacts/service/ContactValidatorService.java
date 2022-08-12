package com.koombea.contacts.service;

import com.koombea.contacts.exception.InvalidFieldException;
import com.koombea.contacts.model.Contact;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactValidatorService {

    public void validateContact(Contact contact) {
        validate(contact, FieldPattern.PHONE_PATTERN, contact.getPhone());
        validate(contact, FieldPattern.NAME_PATTERN, contact.getName());
        validate(contact, FieldPattern.DATE_OF_BIRTH_PATTERN, contact.getDateOfBirth());
        validate(contact, FieldPattern.EMAIL_PATTERN, contact.getEmail());
    }

    private void validate(Contact contact, FieldPattern fieldPattern, String fieldValue) {
        Matcher matcher = fieldPattern.getPattern().matcher(fieldValue);
        if (!matcher.matches()) {
            contact.setValid(false);
            contact.setErrorMessage(String.format("%s is invalid: %s", fieldPattern.getFieldName(), fieldValue));
        }
    }

    enum FieldPattern {

        PHONE_PATTERN("phone", Pattern.compile("\\(\\+\\d{2}\\)\\s\\d{3}((\\-)|(\\s))\\d{3}((\\-)|(\\s))\\d{2}((\\-)|(\\s))\\d{2}(($)|(\\s\\d{2}))")),
        NAME_PATTERN("name", Pattern.compile("^([A-Z-a-z-\\s]+)$")),
        DATE_OF_BIRTH_PATTERN("dateOfBirth", Pattern.compile("(\\d{8})|(\\d{4}-\\d{2}-\\d{2})")),
        EMAIL_PATTERN("email", Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b"));

        private final Pattern pattern;
        private final String fieldName;

        FieldPattern(String fieldName, Pattern pattern) {
            this.pattern = pattern;
            this.fieldName = fieldName;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public String getFieldName() {
            return fieldName;
        }
    }
}
