package com.koombea.contacts.api.mapper;

import com.koombea.contacts.api.dto.UploadResponse;
import com.koombea.contacts.model.Upload;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UploadMapper {

    UploadMapper mapper = Mappers.getMapper(UploadMapper.class);

    UploadResponse toResponse(Upload upload);
}
