package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Order;
import com.app.entities.Order_Item;

public interface OrderItemRepository extends JpaRepository<Order_Item, Long> {

	List<Order_Item> findAllByOrder(Order order);

}
