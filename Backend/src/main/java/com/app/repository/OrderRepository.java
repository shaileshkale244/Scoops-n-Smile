package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;
import com.app.entities.Order;
import com.app.entities.OrderStatus;

public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findAllByCustomer(Customer customer);
	
	List<Order> findAllByStatus(OrderStatus status);

}
