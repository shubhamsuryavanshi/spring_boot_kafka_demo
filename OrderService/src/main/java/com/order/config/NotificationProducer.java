package com.order.config;

import com.order.event.EmailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);

    @Value("${spring.kafka.producer.topic.name.two}")
    private String topic;
    private KafkaTemplate<String, EmailEvent>  emailKafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, EmailEvent> emailKafkaTemplate) {
        this.emailKafkaTemplate = emailKafkaTemplate;
    }

    public void sendNotification(EmailEvent emailEvent){
        log.info(String.format("Notification event -> ",emailEvent.toString()));

        Message<EmailEvent> emailMessage = MessageBuilder
                            .withPayload(emailEvent)
                            .setHeader(KafkaHeaders.TOPIC,topic)
                            .build();
        emailKafkaTemplate.send(emailMessage);
    }
}
