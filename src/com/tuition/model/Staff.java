package com.tuition.model;

public abstract class Staff {
	private String name, staffID;
	private double salaryRate, salary;

	public Staff(String name, String staffID, double salaryRate, double salary) {
		this.name = name;
		this.salaryRate = salaryRate;
		this.salary = salary;
		this.staffID = staffID;
	}// end of the Default constructor block

	public String toString() {
		return name + "," + staffID + "," + salaryRate + "," + salary + "\n";
	}

	public Staff(String name, String staffID, double salary) {
		this(name, staffID, 1, salary);
	}// end of the constructor

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getName() {
		return name;
	}

	public double getSalaryRate() {
		return salaryRate;
	}

	public void setSalaryRate(double salaryRate) {
		this.salaryRate = salaryRate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public abstract void computeSalary();

}
