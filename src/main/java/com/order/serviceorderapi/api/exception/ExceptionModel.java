package com.order.serviceorderapi.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
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
