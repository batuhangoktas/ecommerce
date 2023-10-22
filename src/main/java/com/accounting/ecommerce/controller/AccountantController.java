package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.AccountantDTO;
import com.accounting.ecommerce.service.AccountantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Accountant service controller
 *
 * @author batuhangoktas
 * @version 1.0.0
 * @since 2023-10-22
 */
@RestController
@RequestMapping("/accountant")
@Tag(name = "AccountantController API", description = "Accountant Service API")
public class AccountantController implements BaseController {

    @Autowired
    AccountantService accountantService;

    /**
     * Accountant is create
     *
     * @param accountantDTO Accountant FirstName, LastName, Email, Password
     * @return ResponseEntity Accountant Info
     */
    @PostMapping("/register")
    @Operation(summary = "Register Accountant", description = "Register Accountant Service Call")
    public ResponseEntity<AccountantDTO> create(@RequestBody @Valid AccountantDTO accountantDTO) {
        return new ResponseEntity<>(accountantService.create(accountantDTO), HttpStatus.CREATED);
    }
}
