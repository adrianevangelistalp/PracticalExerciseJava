package com.backend.test.customer.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message) {
        super(message);
    }
}
