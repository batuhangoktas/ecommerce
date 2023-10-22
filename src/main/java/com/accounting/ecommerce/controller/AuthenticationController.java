package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.request.AuthRequest;
import com.accounting.ecommerce.service.UserService;
import com.accounting.ecommerce.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication service controller
 *
 * @author batuhangoktas
 * @version 1.0.0
 * @since 2023-10-22
 */
@RestController
@Tag(name = "AuthenticationController API", description = "Authentication Service API")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    /**
     * Accountant Login
     *
     * @param authRequest Accountant Email, Password
     * @return Accountant Jwt Token
     */
    @PostMapping("/login")
    @Operation(summary = "Login Accountant", description = "Login Accountant Service Call")
    public String createToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}
