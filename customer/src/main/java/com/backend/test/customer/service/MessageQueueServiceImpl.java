package com.backend.test.customer.service;

import com.backend.test.customer.messaging.publisher.RabbitMQProducer;
import com.backend.test.customer.model.messages.AccountStateRequestMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageQueueServiceImpl implements MessageQueueService{
    private final RabbitMQProducer rabbitMQProducer;

    @Override
    public void sendAccountStateRequest(AccountStateRequestMessage accountStateRequestMessage) {
        rabbitMQProducer.send(accountStateRequestMessage);
    }
}
