package com.kaanaydemir.orderservice.kafka;

import com.kaanaydemir.orderservice.event.OrderPlacedEvent;
import com.kaanaydemir.orderservice.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final RabbitTemplate rabbitTemplate;
    private final OrderService orderService;

    @Autowired
    public KafkaConsumer(RabbitTemplate rabbitTemplate, OrderService orderService) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderService = orderService;
    }

    @PostConstruct
    public void init() {
        // initialization logic if any
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "order-placed", durable = "true"))
    public void consume(OrderPlacedEvent event) {
        logger.info("Received OrderPlacedEvent: {}", event);
        // original business logic
        orderService.processOrder(event);
    }

    public void sendMessage(String routingKey, String message) {
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}