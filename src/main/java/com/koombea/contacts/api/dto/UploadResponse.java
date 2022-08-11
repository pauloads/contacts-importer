package com.koombea.contacts.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.koombea.contacts.model.UploadStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadResponse {

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String fileName;
    private UploadStatus status;
    private String failureReason;

}
