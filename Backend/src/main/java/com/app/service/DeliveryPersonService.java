package com.app.service;

import java.util.List;

import com.app.entities.Delivery_Person;

public interface DeliveryPersonService {

	List<Delivery_Person> getAllDeliveryPerson();

	Delivery_Person getDeliverypersonById(Long id);

	Delivery_Person addNewDeliveryperson(Delivery_Person delivery_Person);

	Delivery_Person updateDeliveryperson(Long did,Delivery_Person delivery_Person);

	String deleteDeliveryperson(Long id);

}
