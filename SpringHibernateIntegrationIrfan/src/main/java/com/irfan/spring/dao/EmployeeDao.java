package com.irfan.spring.dao;
import java.util.List;

import com.irfan.spring.model.Employee;



public interface EmployeeDao {

	void saveEmployee(com.irfan.spring.model.Employee employee);
	
	List<Employee> findAllEmployees();
	
	void deleteEmployeeBySsn(String ssn);
	
	Employee findBySsn(String ssn);
	
	void updateEmployee(Employee employee);
}
