package com.koombea.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Entity
@Table(name = "contacts", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "user_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    private String phone;

    private String address;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "credit_card_network")
    private String creditCardNetwork;

    private String email;

    @ManyToOne
    private User user;

    @Column(name = "credit_card_hash")
    private String creditCardHash;
}
