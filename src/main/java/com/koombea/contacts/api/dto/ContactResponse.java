package com.koombea.contacts.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactResponse {

    private String name;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private String phone;

    private String address;

    @JsonProperty("credit_card_number")
    private String creditCardNumber;

    @JsonProperty("credit_card_network")
    private String creditCardNetwork;

    private String email;

    @JsonProperty("error_message")
    private String errorMessage;

}
