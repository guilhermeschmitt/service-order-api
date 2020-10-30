package com.order.serviceorderapi.api.exception;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.order.serviceorderapi.api.exception.ExceptionModel.ExceptionField;
import com.order.serviceorderapi.domain.exception.DomainCustomException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(DomainCustomException.class)
	public ResponseEntity<Object> handleDomainCustomException(DomainCustomException ex, WebRequest request) {
		ExceptionModel model = new ExceptionModel();

		model.setTitle(ex.getMessage());
		model.setDateHour(OffsetDateTime.now());
		model.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return handleExceptionInternal(ex, model, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ExceptionField> exceptionFields = ex.getBindingResult().getAllErrors().stream().map(error -> {
			ExceptionField field = new ExceptionModel.ExceptionField();
			
			field.setField(((FieldError) error).getField());
			field.setMessage(messageSource.getMessage(error, LocaleContextHolder.getLocale()));
			
			return field;
		}).collect(Collectors.toList());
		
		ExceptionModel model = new ExceptionModel();

		model.setStatus(status.value());
		model.setFields(exceptionFields);
		model.setDateHour(OffsetDateTime.now());
		model.setTitle("Campos inválidos! Faça o preenchimento correto dos campos listados e tente novamente.");
		
		return super.handleExceptionInternal(ex, model, headers, status, request);
	}
	
}
