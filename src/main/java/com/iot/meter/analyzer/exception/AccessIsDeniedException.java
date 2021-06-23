package com.iot.meter.analyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessIsDeniedException extends RuntimeException {

	private static final long serialVersionUID = 3375390057580825611L;

	public AccessIsDeniedException() {
		super();
	}

	public AccessIsDeniedException(String message) {
		super(message);
	}

	public AccessIsDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
}
