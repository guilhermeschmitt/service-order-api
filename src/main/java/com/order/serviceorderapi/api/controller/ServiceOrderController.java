package com.order.serviceorderapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.serviceorderapi.domain.model.ServiceOrder;
import com.order.serviceorderapi.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrder save(@Valid @RequestBody ServiceOrder service) {
		return serviceOrderService.save(service);
	}
	
	@GetMapping
	public List<ServiceOrder> findAll() {
		return serviceOrderService.findAll();
	}
	
	@GetMapping("/{serviceOrderId}")
	public ResponseEntity<ServiceOrder> findById(@PathVariable("serviceOrderId") Long serviceOrderId) {
		Optional<ServiceOrder> service = serviceOrderService.findById(serviceOrderId);
		
		if(!service.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(service.get());
	}
}
