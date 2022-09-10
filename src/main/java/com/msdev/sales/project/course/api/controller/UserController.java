package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.domain.entity.UserEntity;
import com.msdev.sales.project.course.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@Valid @RequestBody UserEntity user) {
        String passwordEncripted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncripted);
        return userService.save(user);
    }

}
