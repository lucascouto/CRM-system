package com.lucas.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucas.springdemo.entity.Customer;
import com.lucas.springdemo.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	// need to inject the customerService
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("list")
	public String listCustomers(Model model) {
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
}
