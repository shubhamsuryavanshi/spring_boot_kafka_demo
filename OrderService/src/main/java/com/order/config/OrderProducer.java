package com.order.config;

import com.order.event.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.producer.topic.name.one}")
    private String orderTopic;

    public void sendMessage(OrderEvent orderEvent){
        log.info(String.format("Order Event => %s", orderEvent.toString()));
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC,orderTopic)
                .build();

        kafkaTemplate.send(message);
    }
}
