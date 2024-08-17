package com.app.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.entities.Customer;
import com.app.repository.CustomerRepository;
import com.app.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		return optional.orElseThrow(()->new CustomException("invalid customer id"));
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		Optional<Customer> optional = customerRepository.findByEmail(email);
		return optional.orElseThrow(()->new CustomException("invalid customer email"));
	}

	@Override
	public Customer addNewCustomer(Customer customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long cid,Customer customer) {
		if(customerRepository.existsById(cid)) {
			return customerRepository.save(customer);
		}
		throw new CustomException("invalid customer");
		
	}

	@Override
	public String deleteCustomer(Long id) {
		if(customerRepository.existsById(id)) {
			 customerRepository.deleteById(id);
			 return "Customer Deleted";
		}
		throw new CustomException("Customer Deletion failed");
	}

}
