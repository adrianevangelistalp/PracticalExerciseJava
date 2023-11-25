package com.backend.test.customer.service;

import com.backend.test.customer.model.messages.AccountStateRequestMessage;

public interface MessageQueueService {
    void sendAccountStateRequest(AccountStateRequestMessage accountStateRequestMessage);
}
