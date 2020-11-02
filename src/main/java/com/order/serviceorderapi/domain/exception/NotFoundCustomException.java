package com.order.serviceorderapi.domain.exception;

public class NotFoundCustomException extends DomainCustomException {

	private static final long serialVersionUID = -2320402180300182532L;

	public NotFoundCustomException(String message) {
		super(message);
	}
}
