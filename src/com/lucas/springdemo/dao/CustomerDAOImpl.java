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
		
		// create a query. Sort by last name
		Query<Customer> theQuery = 
				session.createQuery("from Customer order by lastName", Customer.class);
		
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
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// retrieve from database using primary key
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// get customer from database based on PK
		Customer customer = session.get(Customer.class, id);

		// delete customer
		session.delete(customer);
		
		/*
		Query query = session.createQuery("delete from Customer where id := customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
		*/
		
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		Query query = null;
		
		if(searchName != null && searchName.trim().length() > 0) {
			query = session.createQuery("from Customer where lower(firstName)"
					+ "like :theName or lower(lastName) like :theName", Customer.class);
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		}else {
			query = session.createQuery("from Customer", Customer.class);
		}
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}
