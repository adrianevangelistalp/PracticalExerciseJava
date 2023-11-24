package com.backend.test.customer.model.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AccountStateRequestMessage {
    private Long customerId;
    private String from;
    private String to;
}
