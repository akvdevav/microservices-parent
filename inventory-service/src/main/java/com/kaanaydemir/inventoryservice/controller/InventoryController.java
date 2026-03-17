package com.kaanaydemir.inventoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kaanaydemir.inventoryservice.service.InventoryService;
import com.kaanaydemir.inventoryservice.dto.InventoryResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> checkInventory(@PathVariable Long productId) {
        InventoryResponse response = inventoryService.checkInventory(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/decrease")
    public ResponseEntity<Void> decreaseInventory(@Valid @RequestBody InventoryRequest request) {
        inventoryService.decreaseInventory(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "order-placed-queue", durable = "true"))
    public void handleOrderPlaced(String message) {
        inventoryService.processOrderPlaced(message);
    }

    public static class InventoryRequest {
        private Long productId;
        private Integer quantity;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}