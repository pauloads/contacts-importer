package com.koombea.contacts.service.facade;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.exception.InvalidFieldException;
import com.koombea.contacts.model.Upload;
import com.koombea.contacts.service.ContactService;
import com.koombea.contacts.service.ContactValidatorService;
import com.koombea.contacts.service.CreditCardProcessorService;
import com.koombea.contacts.service.UploadService;
import com.koombea.contacts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class FacadeService implements Facade {

    private final UploadService uploadService;

    private final ContactValidatorService contactValidatorService;

    private final CreditCardProcessorService creditCardProcessorService;

    private final ContactService contactService;

    private final UserService userService;

    @Override
    public Upload processUpload(UploadRequest uploadRequest) {
        var upload = uploadService.saveUploadStartedInfo(uploadRequest);
        var contacts = contactService.extractContactsFromFile(uploadRequest);
        var authenticatedUser = userService.getAuthenticatedUser();
        try {
            contacts.stream().forEach(contact -> {

                contactValidatorService.validateContact(contact);
                var creditCardNetwork = creditCardProcessorService.obtainCreditCardNetwork(contact.getCreditCardNumber());
                contact.setCreditCardNetwork(creditCardNetwork);
                var creditCardNumber = contact.getCreditCardNumber();
                var creditCardLastFourNumbers = creditCardProcessorService.obtainLastFourDigits(creditCardNumber);
                contact.setCreditCardNumber(creditCardLastFourNumbers);
                var creditCardHash = creditCardProcessorService.encryptCreditCardNumber(creditCardNumber);
                contact.setCreditCardHash(creditCardHash);
                contact.setUser(authenticatedUser);

            });
            contactService.saveContacts(contacts);
            return uploadService.saveUploadFinishedInfo(upload);
        } catch (InvalidFieldException ex) {
            log.error("error during field validation: {}", ex.getMessage());
            return uploadService.saveUploadFailedInfo(upload, ex.getMessage());
        } catch (DataIntegrityViolationException ex) {
            return uploadService.saveUploadFailedInfo(upload, "Duplicated contact email");
        }
    }
}
