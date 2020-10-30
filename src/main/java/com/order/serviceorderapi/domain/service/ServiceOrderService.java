package com.order.serviceorderapi.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.serviceorderapi.domain.exception.DomainCustomException;
import com.order.serviceorderapi.domain.model.Client;
import com.order.serviceorderapi.domain.model.ServiceOrder;
import com.order.serviceorderapi.domain.model.ServiceOrderStatus;
import com.order.serviceorderapi.domain.repository.ClientRepository;
import com.order.serviceorderapi.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {

	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public ServiceOrder save(ServiceOrder service) {
		Client client = clientRepository.findById(service.getClient().getId()).orElseThrow(() -> new DomainCustomException("Cliente não encontrado!"));
		
		service.setClient(client);
		service.setOpenDate(OffsetDateTime.now());
		service.setStatus(ServiceOrderStatus.OPEN);
		
		return serviceOrderRepository.save(service);
	}

	public List<ServiceOrder> findAll() {
		return serviceOrderRepository.findAll();
	}

	public Optional<ServiceOrder> findById(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId);
	}
	
}
