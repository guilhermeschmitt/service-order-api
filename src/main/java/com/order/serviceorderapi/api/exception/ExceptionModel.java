package com.order.serviceorderapi.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ExceptionModel {

	private String title;
	private Integer status;
	private LocalDateTime dateHour;
	private List<ExceptionField> fields;
	
	
	@Data
	public static class ExceptionField {
		private String field;
		private String message;
	}
}
