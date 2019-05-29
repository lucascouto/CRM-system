package com.lucas.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lucas.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// need to inject hibernate session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = 
				session.createQuery("from Customer", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// save the customer to DB
		session.save(customer);
		
	}

}
