package com.kaanaydemir.orderservice.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;

import com.kaanaydemir.inventoryservice.dto.InventoryResponse;
import com.kaanaydemir.inventoryservice.dto.InventoryRequest;

@Service
public class InventoryServiceProxy {

    private static final String QUEUE_NAME = "inventory";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queuesToDeclare = @Queue(name = QUEUE_NAME, durable = "true"))
    public void listen(InventoryResponse response) {
        // business logic unchanged
    }

    public void sendInventoryRequest(InventoryRequest request) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, request);
    }
}