package com.agile.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(ControllerException.class);

	public ControllerException() {
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

	public ControllerException(HttpStatus status, String string) {
		super(string + " ErrorCode:" + status);	
	}

	public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
