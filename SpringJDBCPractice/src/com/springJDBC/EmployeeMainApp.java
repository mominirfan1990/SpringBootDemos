package com.springJDBC;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeMainApp {

	public static void main(String[] args) 
	{
		AbstractApplicationContext contex= new ClassPathXmlApplicationContext("employeeBean.xml");
		EmployeeDAOTemplate employeeDAOTemplate= contex.getBean(EmployeeDAOTemplate.class);
		
		int salarysum = employeeDAOTemplate.getEmployeeSumSalary();
		System.out.println("Sum of Employee Slary is "+salarysum);
		
		contex.close();
	}

}
