package com.order.serviceorderapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.serviceorderapi.domain.model.Comment;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {

}
