package com.iot.meter.analyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseStatusException {

    public CustomGlobalExceptionHandler(HttpStatus status) {
        super(status);
    }

    public CustomGlobalExceptionHandler(HttpStatus status, String reason) {
        super(status, reason);
    }

    public CustomGlobalExceptionHandler(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public CustomGlobalExceptionHandler(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
