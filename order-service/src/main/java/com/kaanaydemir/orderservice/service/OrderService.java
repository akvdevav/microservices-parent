package com.kaanaydemir.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void placeOrder(String order) {
        // business logic
        rabbitTemplate.convertAndSend("order-topic", order);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "order-topic", durable = "true"))
    public void listen(String message) {
        // business logic
    }
}