package com.koombea.contacts.repository;

import com.koombea.contacts.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}
