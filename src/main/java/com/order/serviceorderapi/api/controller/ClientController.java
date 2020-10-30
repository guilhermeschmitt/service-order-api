package com.order.serviceorderapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.order.serviceorderapi.domain.model.Client;
import com.order.serviceorderapi.domain.repository.ClientRepository;

@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/clients")
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/clients/{idClient}")
	public Client findById(@PathVariable("idCliente") Long idClient) {
		Optional<Client> client = clientRepository.findById(idClient);
		return client.orElse(null);
	}
	
}
