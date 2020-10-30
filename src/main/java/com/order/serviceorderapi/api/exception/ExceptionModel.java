package com.order.serviceorderapi.api.exception;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ExceptionModel {

	private String title;
	private Integer status;
	private OffsetDateTime dateHour;
	private List<ExceptionField> fields;
	
	
	@Data
	public static class ExceptionField {
		private String field;
		private String message;
	}
}
