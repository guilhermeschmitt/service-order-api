package com.order.serviceorderapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.serviceorderapi.api.model.dto.CommentDTO;
import com.order.serviceorderapi.api.model.form.CommentForm;
import com.order.serviceorderapi.domain.service.CommentService;

@RestController
@RequestMapping("/service-orders/{serviceId}/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public List<CommentDTO> findAll(@PathVariable("serviceId") Long serviceId) {
		return commentService.findAll(serviceId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentDTO save(@PathVariable("serviceId") Long serviceId, @Valid @RequestBody CommentForm comment) {
		return commentService.save(serviceId, comment.getDescription());
	}
	
}
