package com.order.serviceorderapi.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.serviceorderapi.domain.exception.DomainCustomException;
import com.order.serviceorderapi.domain.model.Client;
import com.order.serviceorderapi.domain.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Optional<Client> findById(Long clientId) {
		return clientRepository.findById(clientId);
	}

	public Client save(@Valid Client client) {
		
		if(clientRepository.findByEmail(client.getEmail()) != null)
			throw new DomainCustomException("Já existe um cliente cadastrado com este e-mail. Por favor, insira um novo e-mail.");
		
		return clientRepository.save(client);
	}

	public boolean existsById(Long clientId) {
		return clientRepository.existsById(clientId);
	}

	public Client update(@Valid Client client, Long clientId) {
		client.setId(clientId);
		return clientRepository.save(client);
	}

	public void deleteById(Long clientId) {
		clientRepository.deleteById(clientId);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
}
