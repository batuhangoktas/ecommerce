package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.BillDTO;
import com.accounting.ecommerce.request.PaginationRequest;
import com.accounting.ecommerce.service.BillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillControllerTest {


    @Mock
    BillService billService;

    @Mock
    Authentication authentication;
    @Mock
    SecurityContext securityContext;
    @Mock
    UserDetails userDetails;
    @InjectMocks
    BillController billController;

    @Test
    void create() {

        BillDTO expected = BillDTO.builder().firstName("Batuhan").lastName("Göktaş").email("batuhangoktas@hotmail.com").amount(100.0).productName("ProductName").billNo("BillNo").build();

        when(billService.create(any(), any())).thenReturn(expected);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication().getPrincipal()).thenReturn(userDetails);

        BillDTO request = new BillDTO();
        ResponseEntity<BillDTO> response = billController.create(request);
        BillDTO actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()), () -> assertEquals(expected, actual), () -> assertEquals(expected.getBillNo(), actual.getBillNo()));
    }

    @Test
    void getAllApproved() {

        BillDTO expected = BillDTO.builder().firstName("Batuhan").lastName("Göktaş").email("batuhangoktas@hotmail.com").amount(100.0).productName("ProductName").billNo("BillNo").build();


        Pageable pageable = PageRequest.of(0, 20);
        Page<BillDTO> pageBillDTO = new PageImpl<>(List.of(expected), pageable, 1);
        PaginationRequest paginationRequest = new PaginationRequest();

        when(billService.getAll(paginationRequest, true)).thenReturn(pageBillDTO);

        ResponseEntity<Page<BillDTO>> response = billController.getAllApproved(paginationRequest);
        Page<BillDTO> actual = response.getBody();

        assertAll(() -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected, actual.getContent().get(0)),
                () -> assertEquals(expected.getBillNo(), actual.getContent().get(0).getBillNo()));
    }

    @Test
    void getAllUnapproved() {

        BillDTO expected = BillDTO.builder().firstName("Batuhan").lastName("Göktaş").email("batuhangoktas@hotmail.com").amount(100.0).productName("ProductName").billNo("BillNo").build();


        Pageable pageable = PageRequest.of(0, 20);
        Page<BillDTO> pageBillDTO = new PageImpl<>(List.of(expected), pageable, 1);
        PaginationRequest paginationRequest = new PaginationRequest();

        when(billService.getAll(paginationRequest, false)).thenReturn(pageBillDTO);

        ResponseEntity<Page<BillDTO>> response = billController.getAllUnapproved(paginationRequest);
        Page<BillDTO> actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(expected, actual.getContent().get(0)), () -> assertEquals(expected.getBillNo(), actual.getContent().get(0).getBillNo()));
    }
}