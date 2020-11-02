package com.order.serviceorderapi.api.model.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ServiceOrderForm {

	@NotNull
	private Long clientId;
	
	@NotNull
	private BigDecimal price;
	
	@NotBlank
	private String description;
	
}
