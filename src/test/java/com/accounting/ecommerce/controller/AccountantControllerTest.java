package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.AccountantDTO;
import com.accounting.ecommerce.service.AccountantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountantControllerTest {

    @Mock
    AccountantService accountantService;

    @InjectMocks
    AccountantController accountantController;

    @Test
    void create() {

        AccountantDTO expected = AccountantDTO.builder()
                .firstName("Batuhan")
                .lastName("Göktaş")
                .email("batuhangoktas@hotmail.com")
                .build();

        when(accountantService.create(any())).thenReturn(expected);

        AccountantDTO request = new AccountantDTO();
        ResponseEntity<AccountantDTO> response = accountantController.create(request);
        AccountantDTO actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () ->
                assertEquals(HttpStatus.CREATED, response.getStatusCode()), () ->
                assertEquals(expected, actual), () ->
                assertEquals(expected.getEmail(), actual.getEmail()));

    }
}