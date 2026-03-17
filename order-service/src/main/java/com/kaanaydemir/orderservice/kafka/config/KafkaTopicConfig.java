package com.kaanaydemir.orderservice.config;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.order}")
    private String orderTopicName;

    @Value("${kafka.topic.payment}")
    private String paymentTopicName;

    @PostConstruct
    public void init() {
        // Initialization logic if needed
    }

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic(orderTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic(paymentTopicName, 1, (short) 1);
    }
}