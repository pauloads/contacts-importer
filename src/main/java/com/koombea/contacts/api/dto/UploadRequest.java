package com.koombea.contacts.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadRequest {
    private MultipartFile file;
    private String name;
    private String dateOfBirth;
    private String phone;
    private String address;
    private String creditCardNumber;
    private String email;
}
