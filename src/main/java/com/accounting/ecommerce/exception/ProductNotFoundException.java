package com.accounting.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String productId) {
        super("Product is not found :" + productId);
    }
}