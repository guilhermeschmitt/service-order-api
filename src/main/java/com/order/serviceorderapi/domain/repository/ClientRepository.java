package com.order.serviceorderapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.serviceorderapi.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByEmail(String email);

}
