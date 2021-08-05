package com.irfan.springmvc.dao;

import java.util.List;

import com.irfan.springmvc.model.Customer;

public interface CustomerDao 
{
	public List listAllCustomer();
	
	public void saveOrUpdate(Customer customer);
	
	public Customer findCustomerById(int id);
	
	public void deleteCustomer(int id);
}
