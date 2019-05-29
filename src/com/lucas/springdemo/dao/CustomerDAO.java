package com.lucas.springdemo.dao;

import java.util.List;

import com.lucas.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
}
