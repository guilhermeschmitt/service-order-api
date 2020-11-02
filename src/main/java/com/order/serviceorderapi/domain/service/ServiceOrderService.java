package com.order.serviceorderapi.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.serviceorderapi.api.model.dto.ServiceOrderDTO;
import com.order.serviceorderapi.api.model.form.ServiceOrderForm;
import com.order.serviceorderapi.domain.exception.DomainCustomException;
import com.order.serviceorderapi.domain.exception.NotFoundCustomException;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ServiceOrderDTO save(ServiceOrderForm serviceForm) {
		ServiceOrder service = this.formMapper(serviceForm);
		
		Client client = clientRepository.findById(service.getClient().getId()).orElseThrow(() -> new DomainCustomException("Cliente não encontrado!"));
		
		service.setClient(client);
		service.setOpenDate(OffsetDateTime.now());
		service.setStatus(ServiceOrderStatus.OPEN);
		
		return this.mapper(serviceOrderRepository.save(service));
	}

	public List<ServiceOrderDTO> findAll() {
		List<ServiceOrder> services = serviceOrderRepository.findAll();
		return this.mapperList(services);
	}

	public Optional<ServiceOrder> findById(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId);
	}
	
	public ServiceOrderDTO mapper(ServiceOrder service) {
		return modelMapper.map(service, ServiceOrderDTO.class);
	}
	
	public void changeStatusService(Long serviceOrderId, ServiceOrderStatus status) {
		ServiceOrder serviceOrder = this.findServiceOrder(serviceOrderId);
		
		if(!serviceOrder.getStatus().equals(ServiceOrderStatus.OPEN))
			throw new DomainCustomException("Service order is not open!");
			
		serviceOrder.setStatus(status);
		serviceOrder.setEndDate(OffsetDateTime.now());

		serviceOrderRepository.save(serviceOrder);
	}

	public List<ServiceOrderDTO> mapperList(List<ServiceOrder> services) {
		return services.stream().map(service -> this.mapper(service)).collect(Collectors.toList());
	}

	public ServiceOrder formMapper(ServiceOrderForm serviceForm) {
		return modelMapper.map(serviceForm, ServiceOrder.class);
	}
	
	private ServiceOrder findServiceOrder(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId).orElseThrow(() -> new NotFoundCustomException("Service order not found!"));
	}

}
