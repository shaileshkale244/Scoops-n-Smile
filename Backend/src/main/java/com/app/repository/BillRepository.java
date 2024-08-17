package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Bill;
import com.app.entities.Order;

public interface BillRepository extends JpaRepository<Bill, Long> {

	Bill findByOrder(Order order);

}
