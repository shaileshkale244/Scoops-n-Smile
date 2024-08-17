package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entities.Admin;
import com.app.entities.Customer;
import com.app.entities.Delivery_Person;
import com.app.repository.AdminRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.DeliveryPersonRepository;

@Service
public class UnifiedUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository; // Assuming you have these repositories
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DeliveryPersonRepository deliveryPersonRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(email).orElse(null);
		if (admin != null) {
			return admin; // Admin implements UserDetails
		}

		Customer customer = customerRepository.findByEmail(email).orElse(null);
		if (customer != null) {
			return customer; // Customer implements UserDetails
		}

		Delivery_Person deliveryPerson = deliveryPersonRepository.findByEmail(email).orElse(null);
		if (deliveryPerson != null) {
			return deliveryPerson; // DeliveryPerson implements UserDetails
		}

		throw new UsernameNotFoundException("User not found with email: " + email);
	}
}