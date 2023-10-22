package com.accounting.ecommerce.event.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    public static final String TOPIC = "UnApprovedBill";

    private final KafkaTemplate<String, BillKafkaEvent> kafkaTemplate;

    public void sendUnApprovedEvent(BillKafkaEvent event) {
        String key = event.getKey();
        kafkaTemplate.send(TOPIC, key, event);
        log.info("Producer produced the message {}", event);
    }


}
