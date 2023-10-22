package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.AccountantDTO;
import com.accounting.ecommerce.request.AuthRequest;
import com.accounting.ecommerce.service.UserService;
import com.accounting.ecommerce.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserService userService;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    AuthenticationController authenticationController;

    @Test
    void createToken() throws Exception {

        String jwtToken = "jwt";
        AccountantDTO accountantDTO = AccountantDTO.builder()
                .email("batuhangoktas@hotmail.com")
                .password("123456")
                .build();

        UserDetails expected = new User(accountantDTO.getEmail(), accountantDTO.getPassword(), new ArrayList<>());

        when(userService.loadUserByUsername(any())).thenReturn(expected);
        when(jwtUtil.generateToken(any())).thenReturn(jwtToken);

        String expectedJwt = jwtUtil.generateToken(expected);

        AuthRequest request = new AuthRequest();
        String actual = authenticationController.createToken(request);

        assertAll(() -> assertNotNull(actual), () ->
                assertEquals(expectedJwt, actual));

    }
}