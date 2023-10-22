package com.accounting.ecommerce.event.kafka;

import lombok.Data;

@Data
public class BillKafkaEvent {
    private String key;
    private String message;
}
