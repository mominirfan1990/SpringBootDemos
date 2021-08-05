package com.irfan.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irfan.spring.dao.EmployeeDaoImpl;
import com.irfan.spring.model.Employee;


@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDaoImpl dao;
	
	public void saveEmployee(Employee employee) {
		dao.saveEmployee(employee);
	}

	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	public void deleteEmployeeBySsn(String ssn) {
		dao.deleteEmployeeBySsn(ssn);
	}

	public Employee findBySsn(String ssn) {
		return dao.findBySsn(ssn);
	}

	public void updateEmployee(Employee employee){
		dao.updateEmployee(employee);
	}
}
