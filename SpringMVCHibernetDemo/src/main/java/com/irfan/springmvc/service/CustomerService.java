package com.irfan.springmvc.service;

import java.util.List;

import com.irfan.springmvc.model.Customer;

public interface CustomerService 
{
	public List listAllCustomer();
	
	public void saveOrUpdate(Customer customer);
	
	public Customer findCustomerById(int id);
	
	public void deleteCustomer(int id);
	
}
