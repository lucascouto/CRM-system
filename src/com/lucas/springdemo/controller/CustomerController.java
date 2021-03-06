package com.lucas.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("showFormForAdd")
	public String showFormForAdd(Model model) {
		
		// create a model attribute to bind form data
		Customer customer = new Customer();
		
		model.addAttribute(customer);
		
		return "customer-form";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		// save the customer using service
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,
									Model model) {
		
		// get the customer from our service
		Customer customer = customerService.getCustomer(id);
		
		//set customer as a model attribute to prepopulate the form
		model.addAttribute(customer);
		
		// send over to our form
		return "customer-form";
	}
	
	@GetMapping("delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		//delele the customer
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("search")
	public String searchCustomers(@RequestParam("searchName") String searchName,
								Model model) {
		
		// search customers from the service
		List<Customer> customers = customerService.searchCustomers(searchName);
		
		// add the customers to the model
		model.addAttribute("customers", customers);

		return "list-customers";
	}
}
