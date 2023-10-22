package com.accounting.ecommerce.event.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishBillEvent(final String message) {
        System.out.println("Publishing unapproved bill event. ");
        BillEvent billEvent = new BillEvent(this, message);
        applicationEventPublisher.publishEvent(billEvent);
    }
}
