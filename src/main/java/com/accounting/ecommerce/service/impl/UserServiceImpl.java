package com.accounting.ecommerce.service.impl;

import com.accounting.ecommerce.data.entity.Accountant;
import com.accounting.ecommerce.repository.AccountantRepository;
import com.accounting.ecommerce.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    AccountantRepository accountantRepository;

    @PostConstruct
    public void initialize() {
        accountantRepository.save(
                Accountant.builder()
                        .email("admin")
                        .password("$2y$10$wcKLx/DpbAUCXZNhoOr3FO0OnjzWvuJBtTjSRFM1Sl/OISAA0ESmK")
                        .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accountant accountant = accountantRepository.findByEmail(username);
        return new User(accountant.getEmail(), accountant.getPassword(), new ArrayList<>());
    }
}