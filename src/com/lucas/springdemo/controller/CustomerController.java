package com.lucas.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucas.springdemo.dao.CustomerDAO;
import com.lucas.springdemo.entity.Customer;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	// need to inject the customerDAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("list")
	public String listCustomers(Model model) {
		
		// get customers from DAO
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
}
