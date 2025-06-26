package com.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.producer.topic.name.one}")
    private String orderTopic;

    @Value("${spring.kafka.producer.topic.name.two}")
    private String mailTopic;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(orderTopic).build();
    }

    @Bean
    public NewTopic notificationTopic( ){
        return TopicBuilder.name(mailTopic).build();
    }
}
