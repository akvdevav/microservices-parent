package com.kaanaydemir.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kaanaydemir.orderservice.dto.OrderRequest;
import com.kaanaydemir.orderservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Transactional
    public void placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
    }
}