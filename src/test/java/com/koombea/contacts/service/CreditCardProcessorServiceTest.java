package com.koombea.contacts.service;

import com.koombea.contacts.exception.UnsupportedCreditCardNetworkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.koombea.contacts.model.CreditCardNetword.AMERICAN_EXPRESS;
import static com.koombea.contacts.model.CreditCardNetword.MAESTRO;
import static com.koombea.contacts.model.CreditCardNetword.MASTERCARD;
import static com.koombea.contacts.model.CreditCardNetword.VISA;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreditCardProcessorServiceTest {

    @InjectMocks
    public CreditCardProcessorService creditCardProcessorService;

    @Test
    public void shouldReturnUnsupportedWhenCreditCardNetworkIsUnsupported() {
        assertEquals("UNSUPPORTED", creditCardProcessorService.obtainCreditCardNetwork("0000000000000000"));
    }

    @Test
    public void shouldRecognizeVisa() {
        var creditCardNetword = creditCardProcessorService.obtainCreditCardNetwork("4929698969105189");
        assertEquals(VISA.name(), creditCardNetword);
    }

    @Test
    public void shouldRecognizeMastercard() {
        var creditCardNetword = creditCardProcessorService.obtainCreditCardNetwork("5143005771497241");
        assertEquals(MASTERCARD.name(), creditCardNetword);
    }

    @Test
    public void shouldRecognizeAmericanExpress() {
        var creditCardNetword = creditCardProcessorService.obtainCreditCardNetwork("343598417513081");
        assertEquals(AMERICAN_EXPRESS.name(), creditCardNetword);
    }

    @Test
    public void shouldRecognizeMaestro() {
        var creditCardNetword = creditCardProcessorService.obtainCreditCardNetwork("6759649826438453");
        assertEquals(MAESTRO.name(), creditCardNetword);
    }
}