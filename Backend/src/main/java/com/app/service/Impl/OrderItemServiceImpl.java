package com.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.entities.Order;
import com.app.entities.Order_Item;
import com.app.repository.OrderItemRepository;
import com.app.repository.OrderRepository;
import com.app.service.OrderItemService;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository itemRepository;

	@Override
	public List<Order_Item> getAllOrderItemsByOrder(Long oid) {
		Order order = orderRepository.findById(oid).orElseThrow();
		return itemRepository.findAllByOrder(order);
	}

	@Override
	public Order_Item getOrderItemById(Long id) {
		return itemRepository.findById(id).orElseThrow();
	}

	@Override
	public Order_Item createOrderItem(Order_Item order_Item) {
		return itemRepository.save(order_Item);
	}

	@Override
	public Order_Item updateOrderItem(Order_Item updatedOrder_Item) {
		return itemRepository.save(updatedOrder_Item);
	}

	@Override
	public String deleteOrderItem(Long id) {
		if (itemRepository.existsById(id)) {
			itemRepository.deleteById(id);
			return "orderItem deleted";
		}
		throw new CustomException("order Item Not deleted");
	}

}
