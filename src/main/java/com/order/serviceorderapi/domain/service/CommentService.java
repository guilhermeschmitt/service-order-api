package com.order.serviceorderapi.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.serviceorderapi.api.model.dto.CommentDTO;
import com.order.serviceorderapi.domain.exception.NotFoundCustomException;
import com.order.serviceorderapi.domain.model.Comment;
import com.order.serviceorderapi.domain.model.ServiceOrder;
import com.order.serviceorderapi.domain.repository.CommentRepository;
import com.order.serviceorderapi.domain.repository.ServiceOrderRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CommentDTO save(Long serviceOrderId, String description) {
		ServiceOrder service = serviceOrderRepository.findById(serviceOrderId).orElseThrow(() -> new NotFoundCustomException("Service order not found!"));

		Comment comment = new Comment();
		comment.setService(service);
		comment.setDescription(description);
		comment.setSendDate(OffsetDateTime.now());
		
		return this.mapper(commentRepository.save(comment));
	}
	
	public List<CommentDTO> findAll(Long serviceId) {
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceId).orElseThrow(() -> new NotFoundCustomException("Service order not found!"));
		return this.mapperList(serviceOrder.getComments());
	}
	
	public List<CommentDTO> mapperList(List<Comment> comments) {
		return comments.stream().map(comment -> this.mapper(comment)).collect(Collectors.toList());
	}

	public CommentDTO mapper(Comment comment) {
		return modelMapper.map(comment, CommentDTO.class);
	}
}
