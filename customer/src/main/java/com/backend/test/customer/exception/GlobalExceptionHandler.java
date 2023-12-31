package com.backend.test.customer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // specific exceptions
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorMessage> validationException (CustomerNotFoundException ex, WebRequest request) {
        ErrorMessage message = buildError(ex, request);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ErrorMessage> validationException (ReportNotFoundException ex, WebRequest request) {
        ErrorMessage message = buildError(ex, request);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReportAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> validationException (ReportAlreadyExistsException ex, WebRequest request) {
        ErrorMessage message = buildError(ex, request);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    // global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception exception,
                                                              WebRequest webRequest){
        ErrorMessage errorMessage = buildError(exception, webRequest);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ErrorMessage buildError(Exception exception, WebRequest webRequest) {
        return new ErrorMessage(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}