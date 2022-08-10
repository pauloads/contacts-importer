package com.koombea.contacts.api.controller;

import com.koombea.contacts.api.dto.UserRequest;
import com.koombea.contacts.api.dto.UserResponse;
import com.koombea.contacts.api.mapper.UserMapper;
import com.koombea.contacts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        var user = userService.saveUser(userMapper.toEntity(userRequest));
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(userMapper.toResponse(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try {
            var user = userMapper.toResponse(userService.getUserById(id));
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException ex) {
            log.error("User {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", ex);
        }
    }
}
