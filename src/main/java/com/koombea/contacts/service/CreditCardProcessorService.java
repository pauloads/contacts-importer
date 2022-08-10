package com.koombea.contacts.service;

import com.koombea.contacts.exception.UnsupportedCreditCardNetworkException;
import com.koombea.contacts.model.CreditCardNetword;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CreditCardProcessorService {

    public CreditCardNetword obtainCreditCardNetwork(String creditCardNumber) {
        for (CreditCardNetword creditCardNetword : CreditCardNetword.values()) {
            var pattern = Pattern.compile(creditCardNetword.getRegex());
            var matcher = pattern.matcher(creditCardNumber.replace(" ", ""));
            if (matcher.matches()) {
                return creditCardNetword;
            }
        }
        throw new UnsupportedCreditCardNetworkException("Error processing credit card: unsupported credit card network");
    }
}
