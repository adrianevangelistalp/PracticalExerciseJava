package com.backend.test.account.model.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStateRequestMessage {
    private Long customerId;
    private String from;
    private String to;
}
