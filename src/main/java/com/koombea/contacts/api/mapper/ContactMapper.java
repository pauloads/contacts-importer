package com.koombea.contacts.api.mapper;

import com.koombea.contacts.api.dto.ContactResponse;
import com.koombea.contacts.model.Contact;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactMapper {

    ContactResponse toResponse(Contact contacts);
}
