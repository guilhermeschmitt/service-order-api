package com.order.serviceorderapi.api.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.order.serviceorderapi.domain.model.ServiceOrderStatus;

import lombok.Data;

@Data
public class ServiceOrderDTO {

	private Long id;
	private ClientDTO client;
	private BigDecimal price;
	private String description;
	private OffsetDateTime endDate;
	private OffsetDateTime openDate;
	private ServiceOrderStatus status;
	
}
