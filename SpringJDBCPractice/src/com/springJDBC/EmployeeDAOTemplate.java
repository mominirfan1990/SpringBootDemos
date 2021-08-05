package com.springJDBC;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOTemplate 
{
	
	//private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate= new JdbcTemplate(dataSource);
		//this.dataSource=dataSource;
	}
	public int getEmployeeSumSalary()
	{
		String sql="select sum(salary) from employeetable";
				
		int sum=jdbcTemplate.queryForObject(sql, null, Integer.class);
		return sum;
	}
	
}
