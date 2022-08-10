package com.koombea.contacts.exception;

public class UnsupportedCreditCardNetworkException extends RuntimeException{
    public UnsupportedCreditCardNetworkException(String message) {
        super(message);
    }
}
