package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Delivery_Person;

public interface DeliveryPersonRepository extends JpaRepository<Delivery_Person, Long> {
	Optional<Delivery_Person> findByEmail(String email);
}
