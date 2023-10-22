package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.ProductDTO;
import com.accounting.ecommerce.request.PaginationRequest;
import com.accounting.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Product service controller
 *
 * @author batuhangoktas
 * @version 1.0.0
 * @since 2023-10-22
 */
@RestController
@RequestMapping("/product")
@Tag(name = "ProductController API", description = "Products Service API")
public class ProductController implements BaseController {

    @Autowired
    ProductService productService;

    /**
     * Product Create
     *
     * @param productDTO Product Info (Name, Price)
     * @return Product Info
     */
    @PostMapping
    @Operation(summary = "Product Create", description = "Product Create Service Call")
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {
        return new ResponseEntity<>(productService.create(productDTO), HttpStatus.CREATED);
    }

    /**
     * Product Update
     *
     * @param id         Product Id
     * @param productDTO Product Info (Name, Price)
     * @return Product Info
     */
    @PutMapping("/{id}")
    @Operation(summary = "Product Update", description = "Product Update Service Call")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(productDTO, id));
    }

    /**
     * Product Get
     *
     * @param id Product Id
     * @return Product Info
     */
    @GetMapping("/{id}")
    @Operation(summary = "Product", description = "Product Service Call")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    /**
     * Product List
     *
     * @param paginationRequest Page and PageSize values
     * @return Product List
     */
    @GetMapping("/list")
    @Operation(summary = "Product List", description = "Product List Service Call")
    public ResponseEntity<Page<ProductDTO>> getAll(
            @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(productService.getAll(paginationRequest));
    }

    /**
     * Product Delete
     *
     * @param id Product Id
     * @return noContent 204
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Product Delete", description = "Product Delete Service Call")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
