package com.tuition.model;
public class CasualStaff extends Staff 
{
	public CasualStaff(String name,String staffID,double salaryRate,double salary)
	{
		super(name,staffID,salaryRate,salary);
		
	}//end of the default constructor
	public void computeSalary()
	{
		double salary=0.0;
		super.setSalary(salary);
	}
}
