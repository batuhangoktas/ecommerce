package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.BillDTO;
import com.accounting.ecommerce.request.PaginationRequest;
import com.accounting.ecommerce.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Bill service controller
 *
 * @author batuhangoktas
 * @version 1.0.0
 * @since 2023-10-22
 */
@RestController
@RequestMapping("/bill")
@Tag(name = "BillController API", description = "Bill Service API")
public class BillController implements BaseController {

    @Autowired
    BillService billService;

    /**
     * Bill Create
     *
     * @param billDTO Bill Info (Firstname, lastname, email, amount, billNo...)
     * @return Bill Info
     */
    @PostMapping
    @Operation(summary = "Bill Create", description = "Bill Create Service Call")
    public ResponseEntity<BillDTO> create(@RequestBody @Valid BillDTO billDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return new ResponseEntity<>(billService.create(billDTO, userDetails.getUsername()), HttpStatus.CREATED);
    }

    /**
     * Approved Bill List
     *
     * @param paginationRequest Page and PageSize values
     * @return Approved Bill List
     */
    @GetMapping("/list/approved")
    @Operation(summary = "Approved Bill List", description = "Approved Bill List Service Call")
    public ResponseEntity<Page<BillDTO>> getAllApproved(
            @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(billService.getAll(paginationRequest, true));
    }

    /**
     * UnApproved Bill List
     *
     * @param paginationRequest Page and PageSize values
     * @return UnApproved Bill List
     */
    @GetMapping("/list/unapproved")
    @Operation(summary = "UnApproved Bill List", description = "UnApproved Bill List Service Call")
    public ResponseEntity<Page<BillDTO>> getAllUnapproved(
            @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(billService.getAll(paginationRequest, false));
    }
}
