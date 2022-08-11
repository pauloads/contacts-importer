package com.koombea.contacts.service.facade;

import com.koombea.contacts.api.dto.UploadRequest;
import com.koombea.contacts.model.Upload;

public interface Facade {

    Upload processUpload(UploadRequest upload);
}
