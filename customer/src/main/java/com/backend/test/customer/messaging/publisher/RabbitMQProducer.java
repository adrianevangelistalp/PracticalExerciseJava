package com.backend.test.customer.messaging.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class RabbitMQProducer {

    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    private final AmqpTemplate rabbitTemplate;

    public RabbitMQProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
