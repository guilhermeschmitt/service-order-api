package com.order.serviceorderapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.order.serviceorderapi.domain.validation.ValidationGroups;

import lombok.Data;

@Data
@Entity
public class Client {
	
	@Id
	@Column(name = "ID_CLIENT")
	@NotNull(groups = ValidationGroups.ClientId.class)
	@SequenceGenerator(allocationSize = 1, name = "ID_CLIENT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_CLIENT")
	private Long id;

	@NotBlank
	@Size(max = 60)
	private String name;
	
	@Email
	@NotBlank
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "TELEPHONE_NUMBER")
	private String telephoneNumber;

}
