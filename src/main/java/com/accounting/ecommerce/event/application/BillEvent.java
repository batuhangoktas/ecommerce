package com.accounting.ecommerce.event.application;

import org.springframework.context.ApplicationEvent;

public class BillEvent extends ApplicationEvent {
    private String message;

    public BillEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}