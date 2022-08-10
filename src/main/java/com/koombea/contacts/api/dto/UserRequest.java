package com.koombea.contacts.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "E-mail cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
