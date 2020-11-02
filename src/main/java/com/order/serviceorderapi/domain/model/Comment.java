package com.order.serviceorderapi.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
	@NotNull
	@Column(name = "ID_COMMENT")
	@SequenceGenerator(allocationSize = 1, name = "ID_COMMENT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_COMMENT")
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_SERVICE_ORDER")
	private ServiceOrder service;

	@Column(name = "SEND_DATE")
	private OffsetDateTime sendDate;

	private String description;
	
}
