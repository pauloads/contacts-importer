package com.koombea.contacts.service;

import com.koombea.contacts.repository.UploadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {

    @Mock
    private UploadRepository uploadRepository;

    @Mock
    private UserService userService;

    @Mock
    private CreditCardProcessorService creditCardProcessorService;

    @InjectMocks
    public UploadService uploadService;

    @Test
    public void shouldSaveUploadFinishedInfo() {

    }

    @Test
    public void shouldSaveUploadFailedInfo() {

    }

    @Test
    public void shouldSaveUploadStartedInfo() {

    }
}