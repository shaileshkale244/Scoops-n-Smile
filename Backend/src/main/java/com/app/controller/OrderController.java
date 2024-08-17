package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderRequest;
import com.app.entities.Order;
import com.app.service.OrderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<?> listAllOrders(){
		try {
			List<Order> allOrders = orderService.getAllOrders();
			return ResponseEntity.status(HttpStatus.OK).body(allOrders);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@GetMapping("/byStatus")
	public ResponseEntity<?> listAllOrdersByStatus(@RequestBody String status){
		try {
			List<Order> allOrdersByStatus = orderService.getAllOrdersByStatus(status);
			return ResponseEntity.status(HttpStatus.OK).body(allOrdersByStatus);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> allOrderByCustomerId(@PathVariable Long customerId){
		try {
			List<Order> allOrdersByCustomer = orderService.getAllOrdersByCustomer(customerId);
			return ResponseEntity.status(HttpStatus.OK).body(allOrdersByCustomer);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@GetMapping("/byOrderId/{ordId}")
	public ResponseEntity<?> getById(@PathVariable Long ordId){
		try {
			Order orderById = orderService.getOrderById(ordId);
			return ResponseEntity.status(HttpStatus.OK).body(orderById);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> addOrder(@RequestBody OrderRequest order ){
		try {
			Order order2 = orderService.createOrder(order);
			return ResponseEntity.status(HttpStatus.OK).body(order2);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@PutMapping
	public ResponseEntity<?> editOrder(@PathVariable Long oid, @RequestBody Order order ){
		try {
			Order updateOrder = orderService.updateOrder(oid,order);
			return ResponseEntity.status(HttpStatus.OK).body(updateOrder);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> removeDeliveryPerson(@PathVariable Long orderId){
		try {
			String deleteOrder = orderService.deleteOrder(orderId);
			return ResponseEntity.status(HttpStatus.OK).body(deleteOrder);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
}
