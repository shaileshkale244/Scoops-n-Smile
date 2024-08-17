package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Delivery;
import com.app.entities.Order;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	Optional<Delivery> findByOrder(Order order);
	
	@Query("SELECT d FROM Delivery d WHERE d.delivery_Person.id = :deliveryPersonId")
    List<Delivery> findDeliveriesByDeliveryPerson(@Param("deliveryPersonId") Long deliveryPersonId);

}
