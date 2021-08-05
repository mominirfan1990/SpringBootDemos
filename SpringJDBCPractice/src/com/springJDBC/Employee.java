package com.springJDBC;

public class Employee {
	private int eid;
	private String ename;
	private int salary;

	public Employee() {
		super();

	}

	public Employee(int eid, String ename, int salary) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + "]";
	}
	
}
