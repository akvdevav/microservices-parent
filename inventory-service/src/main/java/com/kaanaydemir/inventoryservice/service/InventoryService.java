package com.kaanaydemir.inventoryservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import com.kaanaydemir.inventoryservice.repository.InventoryRepository;
import com.kaanaydemir.inventoryservice.model.Inventory;
import com.kaanaydemir.inventoryservice.dto.OrderPlacedEvent;
import jakarta.transaction.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, RabbitTemplate rabbitTemplate) {
        this.inventoryRepository = inventoryRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "inventory-queue", durable = "true"))
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        // Existing business logic unchanged
        // Example: reduce inventory based on order items
        // ...
    }

    @Transactional
    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void sendMessage(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}