package com.kaanaydemir.productservice.service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaanaydemir.productservice.model.Product;
import com.kaanaydemir.productservice.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void sendProductCreatedEvent(Product product) {
        rabbitTemplate.convertAndSend("product-events", product.getId().toString());
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "product-events", durable = "true"))
    public void listen(String message) {
        // handle incoming message
    }
}