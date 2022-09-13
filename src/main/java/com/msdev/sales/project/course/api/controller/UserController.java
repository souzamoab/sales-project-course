package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.api.controller.dto.CredentialsDTO;
import com.msdev.sales.project.course.api.controller.dto.TokenDTO;
import com.msdev.sales.project.course.domain.entity.UserEntity;
import com.msdev.sales.project.course.exception.InvalidPasswordException;
import com.msdev.sales.project.course.service.impl.UserServiceImpl;
import com.msdev.sales.project.course.service.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@Valid @RequestBody UserEntity user) {
        String passwordEncripted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncripted);
        return userService.save(user);
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            UserEntity user = UserEntity.builder().userName(credentialsDTO.getUserName()).password(credentialsDTO.getPassword()).build();
            UserDetails userAuthenticated = userService.authenticate(user);
            String token = jwtService.generateToken(user);

            return new TokenDTO(user.getUserName(), token);
        }catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
