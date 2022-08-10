package com.koombea.contacts.service;

import com.koombea.contacts.model.User;
import com.koombea.contacts.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User getUser() {
        return new User(1L, "arnold.schwarzenegger@koombea.com", "Arnold Schwarzenegger");
    }

    @Test
    public void shouldSaveUser() {
        var user = getUser();
        userService.saveUser(user);
        verify(passwordEncoder).encode(anyString());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void shouldGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(getUser()));
        userService.getUserById(1L);
        verify(userRepository).findById(anyLong());
    }

    @Test
    public void shouldGetUserByEmail() {
        when(userRepository.findByEmail("arnold.schwarzenegger@koombea.com")).thenReturn(Optional.of(getUser()));
        userService.getUserByEmail("arnold.schwarzenegger@koombea.com");
        verify(userRepository).findByEmail(anyString());
    }

}