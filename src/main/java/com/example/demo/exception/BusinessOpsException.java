package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

public class BusinessOpsException extends RestClientException {
    public BusinessOpsException(String message, HttpStatus httpStatus) {
        super(message);
    }


}