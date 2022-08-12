package com.koombea.contacts.repository;

import com.koombea.contacts.model.Upload;
import com.koombea.contacts.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {

    Page<Upload> findAllByUser(User user, Pageable pageable);
}
