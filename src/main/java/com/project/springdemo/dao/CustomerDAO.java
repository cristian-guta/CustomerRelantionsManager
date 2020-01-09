package com.project.springdemo.dao;

import java.util.ArrayList;
import java.util.List;

import com.project.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public Object deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);

}
