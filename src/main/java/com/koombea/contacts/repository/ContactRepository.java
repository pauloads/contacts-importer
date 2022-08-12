package com.koombea.contacts.repository;

import com.koombea.contacts.model.Contact;
import com.koombea.contacts.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findAllByUserAndValid(User user, Pageable pageable, boolean valid);
}
