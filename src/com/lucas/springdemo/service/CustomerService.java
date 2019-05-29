package com.lucas.springdemo.service;

import java.util.List;

import com.lucas.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

}
