package com.app.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.entities.Delivery_Person;
import com.app.repository.DeliveryPersonRepository;
import com.app.service.DeliveryPersonService;

@Service
@Transactional
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

	@Autowired
	private DeliveryPersonRepository deliveryPersonRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<Delivery_Person> getAllDeliveryPerson() {
		return deliveryPersonRepository.findAll();
	}

	@Override
	public Delivery_Person getDeliverypersonById(Long id) {
		Optional<Delivery_Person> optional = deliveryPersonRepository.findById(id);
		return optional.orElseThrow(() -> new CustomException("Invalid Delivery id"));
	}

	@Override
	public Delivery_Person addNewDeliveryperson(Delivery_Person delivery_Person) {
		delivery_Person.setPassword(encoder.encode(delivery_Person.getPassword()));
		return deliveryPersonRepository.save(delivery_Person);
	}

	@Override
	public Delivery_Person updateDeliveryperson(Long did, Delivery_Person delivery_Person) {
		if (deliveryPersonRepository.existsById(did)) {
			return deliveryPersonRepository.save(delivery_Person);
		}
		throw new CustomException("Invalid Delivery Person Id");
	}

	@Override
	public String deleteDeliveryperson(Long id) {
		if (deliveryPersonRepository.existsById(id)) {
			deliveryPersonRepository.deleteById(id);
			return "Delivery person removed";
		}
		throw new CustomException("Delivery Person Custom Operation failed");
	}

}
