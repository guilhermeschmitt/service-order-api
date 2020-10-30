package com.order.serviceorderapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.serviceorderapi.domain.model.Client;
import com.order.serviceorderapi.domain.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping
	public List<Client> findAll() {
		return clientService.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> findById(@PathVariable("clientId") Long clientId) {
		Optional<Client> client = clientService.findById(clientId);
		
		if(client.isPresent())
			return ResponseEntity.ok(client.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@Valid @RequestBody Client client) {
		return clientService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @RequestBody Client client, @PathVariable("clientId") Long clientId) {
		if(!clientService.existsById(clientId))
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(clientService.update(client, clientId));
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId) {
		if(!clientService.existsById(clientId))
			return ResponseEntity.notFound().build();
		
		clientService.deleteById(clientId);
		return ResponseEntity.noContent().build();
	}
	
}
