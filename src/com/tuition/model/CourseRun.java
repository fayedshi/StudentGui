package com.tuition.model;
import java.util.ArrayList;


public class CourseRun 
{
	private String textBook,instructor;
	private double tuitionFee;
	private boolean enrollmentStatus;
	private ArrayList <Student> studentsList;
	public CourseRun(String textBook, String instructor, double tuitionFee,
			boolean enrollmentStatus) 
	{
		
		
		this.textBook = textBook;
		this.instructor = instructor;
		this.tuitionFee = tuitionFee;
		this.enrollmentStatus = enrollmentStatus;
		studentsList=new ArrayList <Student>(20);
	}
	
	public void setStudentsList(Student obj) 
	{
		studentsList.add(obj);
	}

	public String getTextBook() {
		return textBook;
	}
	public void setTextBook(String textBook) {
		this.textBook = textBook;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public double getTuitionFee() {
		return tuitionFee;
	}
	public void setTuitionFee(double tuitionFee) {
		this.tuitionFee = tuitionFee;
	}
	public boolean isEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(boolean enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	
	
	

}
