package com.koombea.contacts.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserResponse {

    @NotBlank(message = "E-mail cannot be empty")
    private String email;
}
