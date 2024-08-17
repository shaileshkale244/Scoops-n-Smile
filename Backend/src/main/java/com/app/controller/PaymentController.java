package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderRequest;
import com.app.dto.OrderResponse;
import com.app.entities.Delivery;
import com.app.entities.Order;
import com.app.service.DeliveryService;
import com.app.service.OrderService;
import com.app.service.PaymentService;
import com.razorpay.RazorpayException;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private DeliveryService deliveryService;

	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestParam("amount") Double amount, @RequestParam("currency") String currency ,
			@RequestBody OrderRequest orderReq) {
		try {
			String order = paymentService.createOrder(amount, currency);
			Order savedOrder = service.createOrder(orderReq);
			OrderResponse response = new OrderResponse(order,savedOrder.getId());
			System.out.println(response);
			return ResponseEntity.ok(response);
		} catch (RazorpayException e) {
			return ResponseEntity.status(500).body("Error creating Razorpay order: " + e.getMessage());
		}
	}

	@PostMapping("/success")
	public ResponseEntity<String> paymentSuccess(@RequestParam String razorpayPaymentId,
			@RequestParam String razorpayOrderId, @RequestParam String razorpaySignature,@RequestParam Long oid) throws RazorpayException {
		// Verify payment success
		paymentService.handlePaymentSuccess(razorpayPaymentId, razorpayOrderId, razorpaySignature);
		Delivery delivery = deliveryService.createDelivery(service.getOrderById(oid));
		return ResponseEntity.ok("Payment successful "+delivery.getId());
	}
}