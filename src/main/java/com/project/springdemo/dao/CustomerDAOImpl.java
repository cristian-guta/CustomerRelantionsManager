package com.project.springdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	// need to inject the session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by last_name", Customer.class);
		
		// execute query
		
		List<Customer> customers = theQuery.getResultList();
		
		// return list of customers
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update customer into db
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve/read data from db using the primary key/id passed into the method
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public Object deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		return null;
	}

	@Override
    public List<Customer> searchCustomers(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<Customer> theQuery=null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theSearchName or lower(lastName) like :theSearchName" , Customer.class);
            theQuery.setParameter("theSearchName", "%" + theSearchName + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        // return the results        
        return customers;
        
        
        
    }
}
