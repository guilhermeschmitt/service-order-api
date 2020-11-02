package com.order.serviceorderapi.api.model.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class CommentDTO {

	private Long id;
	private String description;
	private OffsetDateTime sendDate;
	
}
