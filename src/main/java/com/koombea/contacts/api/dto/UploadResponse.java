package com.koombea.contacts.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.koombea.contacts.model.UploadStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadResponse {

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String fileName;
    private UploadStatus status;
    private String failureReason;

}
