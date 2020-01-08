package com.luv2code.springdemo.dao;

import java.util.ArrayList;
import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public Object deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);

}
