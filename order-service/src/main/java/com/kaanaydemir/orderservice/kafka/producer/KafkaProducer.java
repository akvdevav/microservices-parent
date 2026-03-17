package com.kaanaydemir.orderservice.producer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public KafkaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String queue, String message) {
        rabbitTemplate.convertAndSend(queue, message);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "someTopic", durable = "true"))
    public void listen(String message) {
        // process the received message
    }
}