package com.koombea.contacts.service;

import com.koombea.contacts.model.CreditCardNetword;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CreditCardProcessorService {

    private static final String UNSUPPORTED = "UNSUPPORTED";

    public String obtainCreditCardNetwork(String creditCardNumber) {
        for (CreditCardNetword creditCardNetword : CreditCardNetword.values()) {
            var pattern = Pattern.compile(creditCardNetword.getRegex());
            var matcher = pattern.matcher(creditCardNumber.replace(" ", ""));
            if (matcher.matches()) {
                return creditCardNetword.name();
            }
        }
        return UNSUPPORTED;
    }

    public String encryptCreditCardNumber(String creditCardNumber){
        return DigestUtils.sha3_224Hex(creditCardNumber);
    }

    public String obtainLastFourDigits(String creditCardNumber){
        return creditCardNumber.substring(creditCardNumber.length() - 4);
    }

}
