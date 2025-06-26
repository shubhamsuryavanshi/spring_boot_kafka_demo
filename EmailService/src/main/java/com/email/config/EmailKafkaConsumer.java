package com.email.config;

import com.order.event.EmailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class EmailKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(EmailKafkaConsumer.class);

    @KafkaListener(topics = "notification",groupId = "notify")
    public void consume(EmailEvent emailEvent) {
        log.info(String.format("Notification received ->  %s",emailEvent.toString()));
    }
}
