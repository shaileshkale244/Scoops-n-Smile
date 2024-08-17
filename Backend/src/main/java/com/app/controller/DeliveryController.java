package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AsignDeliveryPerson;
import com.app.dto.DeliveryResponse;
import com.app.dto.OtpVerification;
import com.app.entities.Delivery;
import com.app.service.DeliveryService;
import com.app.service.OrderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private OrderService orderService;

	@PostMapping("/{oid}")
	public String postMethodName(@PathVariable Long oid) {

		Delivery delivery = deliveryService.createDelivery(orderService.getOrderById(oid));
		return "Delivery created" + delivery.getId();
	}

	@GetMapping("/{deliverypersonID}")
	public ResponseEntity<?> getAllDeliveriesByDeliveryPerson(@PathVariable Long deliverypersonID) {
		try {
			List<DeliveryResponse> allDeliveries = deliveryService.getAllByDeliveryPerson(deliverypersonID);
			return ResponseEntity.status(HttpStatus.OK).body(allDeliveries);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> verifyOTP(@RequestBody OtpVerification verification) {

		try {
			Boolean verifyOtp = deliveryService.verifyOtp(verification.getOrderId(), verification.getOtpcode());
			return ResponseEntity.status(HttpStatus.OK).body(verifyOtp);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/asign-delivery-person")
	public ResponseEntity<?> assignDeliveryPerson(@RequestBody AsignDeliveryPerson asignDeliveryPerson) {

		try {
			 Delivery delivery = deliveryService.assignDeliveryPerson(asignDeliveryPerson.getDeliveryPersonId(),asignDeliveryPerson.getOrderId());
			 
			 System.out.println(delivery.getId()+"controller");
			return ResponseEntity.status(HttpStatus.OK).body(delivery);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}


}
