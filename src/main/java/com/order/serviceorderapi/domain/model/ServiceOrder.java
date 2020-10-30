package com.order.serviceorderapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.order.serviceorderapi.domain.validation.ValidationGroups;

import lombok.Data;

@Data
@Entity
public class ServiceOrder {

	@Id
	@Column(name = "ID_SERVICE_ORDER")
	@SequenceGenerator(allocationSize = 1, name = "ID_SERVICE_ORDER")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SERVICE_ORDER")
	private Long id;
	
	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT")
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
	private Client client;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private ServiceOrderStatus status;
	
	@Column(name = "END_DATE")
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime endDate;
	
	@Column(name = "OPEN_DATE")
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime openDate;
	
	@NotNull
	private BigDecimal price;
	
	@NotBlank
	private String description;
	
}
