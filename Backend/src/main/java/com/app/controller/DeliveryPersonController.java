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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Delivery_Person;
import com.app.service.DeliveryPersonService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/delivery_person")
public class DeliveryPersonController {

	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@GetMapping
	public ResponseEntity<?> listAllDeliveryPerson(){
		try {
			List<Delivery_Person> allDeliveryPerson = deliveryPersonService.getAllDeliveryPerson();
			return ResponseEntity.status(HttpStatus.OK).body(allDeliveryPerson);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@GetMapping("/{deliveryPersonId}")
	public ResponseEntity<?> deliveryPersonById(@PathVariable Long deliveryPersonId){
		try {
			Delivery_Person deliverypersonById = deliveryPersonService.getDeliverypersonById(deliveryPersonId);
			return ResponseEntity.status(HttpStatus.OK).body(deliverypersonById);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> addDeliveryPerson(@RequestBody Delivery_Person deliveryPerson ){
		try {
			Delivery_Person newDeliveryperson = deliveryPersonService.addNewDeliveryperson(deliveryPerson);
			return ResponseEntity.status(HttpStatus.OK).body(newDeliveryperson);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@PutMapping
	public ResponseEntity<?> editDeliveryPerson(@PathVariable Long did, @RequestBody Delivery_Person deliveryPerson ){
		try {
			Delivery_Person editDeliveryperson = deliveryPersonService.updateDeliveryperson(did,deliveryPerson);
			return ResponseEntity.status(HttpStatus.OK).body(editDeliveryperson);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@DeleteMapping("/{deliveryPersonId}")
	public ResponseEntity<?> removeDeliveryPerson(@PathVariable Long deliveryPersonId){
		try {
			String deleteDeliveryperson = deliveryPersonService.deleteDeliveryperson(deliveryPersonId);
			return ResponseEntity.status(HttpStatus.OK).body(deleteDeliveryperson);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	
}
