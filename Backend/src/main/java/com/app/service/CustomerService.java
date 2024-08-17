package com.app.service;

import java.util.List;

import com.app.entities.Customer;

public interface CustomerService {
 List<Customer> getAllCustomers();
 
 Customer getCustomerById(Long id);
 
 Customer getCustomerByEmail(String email);
 
 Customer addNewCustomer(Customer customer);
 
 Customer updateCustomer(Long cid,Customer customer);
 
 String deleteCustomer(Long id);
 
}
