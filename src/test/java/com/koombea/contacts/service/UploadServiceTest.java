package com.koombea.contacts.service;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.model.Upload;
import com.koombea.contacts.model.UploadStatus;
import com.koombea.contacts.model.User;
import com.koombea.contacts.repository.UploadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {

    @Mock
    private UploadRepository uploadRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    public UploadService uploadService;

    @Test
    public void shouldSaveUploadFinishedInfo() {
        var upload = new Upload();
        uploadService.saveUploadFinishedInfo(upload);

        verify(uploadRepository).save(upload);
        assertEquals(UploadStatus.FINISHED, upload.getStatus());
    }

    @Test
    public void shouldSaveUploadFailedInfo() {
        var upload = new Upload();
        var errorMessage = "Some error message";
        uploadService.saveUploadFailedInfo(upload, errorMessage);
        verify(uploadRepository).save(upload);
        assertEquals(UploadStatus.FAILED, upload.getStatus());
        assertEquals(errorMessage, upload.getFailureReason());
    }

    @Test
    public void shouldSaveUploadStartedInfo() throws IOException {
        var authenticatedUser = new User();
        var fileName = "data.csv";
        var file = new MockMultipartFile("name", fileName, "text/csv", InputStream.nullInputStream());
        var uploadRequest = new UploadRequest();
        uploadRequest.setFile(file);
        when(userService.getAuthenticatedUser()).thenReturn(authenticatedUser);
        uploadService.saveUploadStartedInfo(uploadRequest);
        verify(uploadRepository).save(any(Upload.class));
    }
}