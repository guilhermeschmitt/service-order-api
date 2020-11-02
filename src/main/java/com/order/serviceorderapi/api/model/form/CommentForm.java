package com.order.serviceorderapi.api.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentForm {

	@NotBlank
	private String description;
	
}
