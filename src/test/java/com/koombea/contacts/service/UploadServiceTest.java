package com.koombea.contacts.service;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.repository.UploadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {

    @Mock
    private ContactValidatorService contactValidatorService;

    @Mock
    private UploadRepository uploadRepository;

    @Mock
    private UserService userService;

    @Mock
    private CreditCardProcessorService creditCardProcessorService;

    @InjectMocks
    public UploadService uploadService;

    @Test
    public void shouldExtractRecordsFromFile() {
        var uploadRequest = getUploadRequest();
        var contacts = uploadService.extractRecordsFromFile(uploadRequest);
        Assertions.assertFalse(contacts.isEmpty());
    }

    private UploadRequest getUploadRequest() {
        var file = new MockMultipartFile("data.csv", getCsvContent().getBytes());
        var uploadRequest = new UploadRequest();
        uploadRequest.setFile(file);
        uploadRequest.setAddress("address");
        uploadRequest.setCreditCardNumber("creditCardNumber");
        uploadRequest.setEmail("email");
        uploadRequest.setName("name");
        uploadRequest.setPhone("phone");
        uploadRequest.setDateOfBirth("dateOfBirth");
        return uploadRequest;
    }

    private String getCsvContent() {
        return "name,dateOfBirth,phone,address,creditCardNumber,email\n" +
                "Amal Fowler,2010-03-19,(+22) 402-156-05-36,874 Facilisis Ave,4929698969105189,mauris.morbi.non@protonmail.couk\n" +
                "Drake Pugh,2012-11-24,(+86) 251 022 98 48 60,Ap #589-9459 Elit. Avenue,5143005771497241,pede.blandit@google.edu\n";
    }

}