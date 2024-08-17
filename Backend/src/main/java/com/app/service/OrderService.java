package com.app.service;

import java.util.List;

import com.app.dto.OrderRequest;
import com.app.entities.Order;

public interface OrderService {

	 List<Order> getAllOrdersByCustomer(Long  cid);
	 
	 List<Order> getAllOrdersByStatus(String status);
	 
	 List<Order> getAllOrders();
	 
	 Order getOrderById(Long id);
	 
	 Order createOrder(Order Order);
	 
	 Order createOrder(OrderRequest Order);
	 
	 Order updateOrder(Long oid,Order Order);
	 
	 String deleteOrder(Long id);
}
