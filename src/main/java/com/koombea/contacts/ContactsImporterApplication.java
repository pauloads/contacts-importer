package com.koombea.contacts;

import com.koombea.contacts.model.User;
import com.koombea.contacts.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ContactsImporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactsImporterApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
          userService.saveUser(new User(null, "freddie.mercury@koombea.com", "pwd#123"));
        };
    }
}
