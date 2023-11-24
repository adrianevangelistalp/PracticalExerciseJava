package com.backend.test.account.messaging.consumer;

import com.backend.test.account.model.messages.AccountStateRequestMessage;
import com.backend.test.account.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQConsumer {
    private final ReportService reportService;
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receivedMessage(AccountStateRequestMessage message) {
        reportService.createAccountsState(message.getCustomerId(), message.getFrom(), message.getTo());
    }
}
