package com.order.serviceorderapi.domain.exception;

public class DomainCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DomainCustomException(String message) {
		super(message);
	}

}
