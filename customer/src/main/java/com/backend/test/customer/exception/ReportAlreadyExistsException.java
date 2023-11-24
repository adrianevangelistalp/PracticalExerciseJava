package com.backend.test.customer.exception;

public class ReportAlreadyExistsException extends RuntimeException{
    public ReportAlreadyExistsException(String message) {
        super(message);
    }
}
