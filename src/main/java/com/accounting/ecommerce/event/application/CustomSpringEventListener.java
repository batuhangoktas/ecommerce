package com.accounting.ecommerce.event.application;

import com.accounting.ecommerce.event.kafka.BillKafkaEvent;
import com.accounting.ecommerce.event.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomSpringEventListener implements ApplicationListener<BillEvent> {

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public void onApplicationEvent(BillEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
        BillKafkaEvent billKafkaEvent = new BillKafkaEvent();
        billKafkaEvent.setKey(UUID.randomUUID().toString());
        billKafkaEvent.setMessage(event.getMessage());
        kafkaProducer.sendUnApprovedEvent(billKafkaEvent);
    }
}