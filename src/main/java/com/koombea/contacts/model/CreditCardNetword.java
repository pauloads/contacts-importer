package com.koombea.contacts.model;

import java.util.regex.Pattern;

public enum CreditCardNetword {
    VISA("^4[0-9]{15}$"),
    MASTERCARD("^5[1-5][0-9]{14}$"),
    AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
    MAESTRO("^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$");

    private String regex;

    CreditCardNetword(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
