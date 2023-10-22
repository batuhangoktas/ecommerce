package com.accounting.ecommerce.controller;

import com.accounting.ecommerce.data.dto.ProductDTO;
import com.accounting.ecommerce.service.ProductService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    void create() {
        ProductDTO expected = ProductDTO.builder()
                .name("1_Product")
                .price(100.0)
                .build();

        when(productService.create(any())).thenReturn(expected);

        ProductDTO request = new ProductDTO();
        ResponseEntity<ProductDTO> response = productController.create(request);
        ProductDTO actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () ->
                assertEquals(HttpStatus.CREATED, response.getStatusCode()), () ->
                assertEquals(expected, actual), () ->
                assertEquals(expected.getName(), actual.getName()));
    }

    @Test
    void updateProduct() {

        ProductDTO expected = ProductDTO.builder()
                .name("1_Product")
                .price(100.0)
                .build();

        when(productService.update(any(), any())).thenReturn(expected);

        ProductDTO request = new ProductDTO();
        ResponseEntity<ProductDTO> response = productController.updateProduct("", request);
        ProductDTO actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () ->
                assertEquals(HttpStatus.OK, response.getStatusCode()), () ->
                assertEquals(expected, actual), () ->
                assertEquals(expected.getName(), actual.getName()));

    }

    @Test
    void getById() {

        ProductDTO expected = ProductDTO.builder()
                .name("1_Product")
                .price(100.0)
                .build();

        when(productService.getById(any())).thenReturn(expected);

        ResponseEntity<ProductDTO> response = productController.getById(any());
        ProductDTO actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () ->
                assertEquals(HttpStatus.OK, response.getStatusCode()), () ->
                assertEquals(expected, actual), () ->
                assertEquals(expected.getName(), actual.getName()));

    }

    @Test
    void getAll() {

        ProductDTO expected = ProductDTO.builder()
                .name("1_Product")
                .price(100.0)
                .build();

        Pageable pageable = PageRequest.of(0, 20);
        Page<ProductDTO> pageProductDTO = new PageImpl<>(List.of(expected), pageable, 1);

        when(productService.getAll(any())).thenReturn(pageProductDTO);

        ResponseEntity<Page<ProductDTO>> response = productController.getAll(any());
        Page<ProductDTO> actual = response.getBody();

        assertAll(() -> assertNotNull(actual), () ->
                assertEquals(HttpStatus.OK, response.getStatusCode()), () ->
                assertEquals(expected, actual.getContent().get(0)), () ->
                assertEquals(expected.getName(), actual.getContent().get(0).getName()));
    }

    @Test
    void deleteById() {

        ResponseEntity<?> response = productController.deleteById(any());

        assertAll(() -> assertNotNull(response), () ->
                assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()), () ->
                assertNotEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()));
    }
}