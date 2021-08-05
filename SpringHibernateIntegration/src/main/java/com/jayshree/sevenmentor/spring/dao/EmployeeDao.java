package com.jayshree.sevenmentor.spring.dao;
import com.jayshree.sevenmentor.spring.model.Employee;
import java.util.List;



public interface EmployeeDao {

	void saveEmployee(com.jayshree.sevenmentor.spring.model.Employee employee);
	
	List<Employee> findAllEmployees();
	
	void deleteEmployeeBySsn(String ssn);
	
	Employee findBySsn(String ssn);
	
	void updateEmployee(Employee employee);
}
