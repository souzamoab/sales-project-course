package com.msdev.sales.project.course.service.impl;

import com.msdev.sales.project.course.domain.entity.UserEntity;
import com.msdev.sales.project.course.domain.repository.UserRepository;
import com.msdev.sales.project.course.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserDetails authenticate(UserEntity user) {
        UserDetails userDetails = loadUserByUsername(user.getUserName());
        boolean isPasswordMatches = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());

        if(isPasswordMatches) {
            return userDetails;
        }

        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found in database"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

}
