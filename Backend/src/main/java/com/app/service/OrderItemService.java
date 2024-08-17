package com.app.service;

import java.util.List;

import com.app.entities.Order_Item;

public interface OrderItemService {

	 List<Order_Item> getAllOrderItemsByOrder(Long oid);
	 
	 Order_Item getOrderItemById(Long id);
	 
	 Order_Item createOrderItem(Order_Item Order_Item);
	 	
	 Order_Item updateOrderItem(Order_Item Order_Item);
	 
	 String deleteOrderItem(Long id);

}
