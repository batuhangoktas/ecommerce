package com.accounting.ecommerce.exception;

public class AccountantAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountantAlreadyExistsException(String accountantId) {
        super("Accountant already exists :" + accountantId);
    }
}
