package com.tuition.model;
import java.util.*;


public class Course 
{
	private String courseName;
	private double tax,taxRate;
	private  int  activeRun2011,activeRun2012 ,category;//activeRun Variables to keep track of the number of runs
	private final static int MAX_RUN=5;
	private  CourseRun [] runs2011 ;
	private  CourseRun [] runs2012 ;
	private Scanner scan;
	public Course(String courseName ,double tax,double taxRate,int category)
	{
		this.courseName=courseName;
		this.tax=tax;
		this.taxRate=taxRate;
		this.category=category;
		this.runs2011=new CourseRun [MAX_RUN];
		this.runs2012=new CourseRun[MAX_RUN];
		this.scan=new Scanner(System.in);
		
	}//end of the constructor block
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public int getActiveRun2011() {
		return activeRun2011;
	}
	public void setActiveRun2011(int activeRun2011) {
		this.activeRun2011 = activeRun2011;
	}
	public int getActiveRun2012() {
		return activeRun2012;
	}
	public void setActiveRun2012(int activeRun2012) {
		this.activeRun2012 = activeRun2012;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public CourseRun[] getRuns2011() {
		return runs2011;
	}
	
	public void setRuns2011(String year,String instructorID) 
	{
		if (year.equals("2011"))
		{
			if (activeRun2011 == MAX_RUN)
			{
				System.out.println("Sorry You Can't Create More Runs " +
						           "For This Year '2011'");
			}
			else
			{
				String textBook;
				boolean enrollmentStatus=true;
				double tuitionFee=500.0;
				System.out.println("Assign TextBook For This Course *OPTIONAL* Enter N To Assign That" +
						           " Later >");
				textBook=scan.next();
				if (textBook.equals("N"))
					textBook=null;
				runs2011[activeRun2011]=new CourseRun(textBook, instructorID, tuitionFee, enrollmentStatus);
				activeRun2011++;
			}
		}
		else
		{
			if (runs2012.length == MAX_RUN)
			{
				System.out.println("Sorry You Can't Create More Runs " +
						           "For This Year '2012'");
			}
			else
			{
				String textBook;
				boolean enrollmentStatus=true;
				double tuitionFee=500.0;
				System.out.println("Assign TextBook For This Course *OPTIONAL* Enter N To Assign That" +
						           " Later >");
				textBook=scan.next();
				if (textBook.equals("N"))
					textBook=null;
				runs2012[activeRun2011]=new CourseRun(textBook, instructorID, tuitionFee, enrollmentStatus);
				activeRun2012++;
			}
		}
	}
	public CourseRun[] getRuns2012() {
		return runs2012;
	}
	public void setRuns2012(CourseRun[] runs2012) {
		this.runs2012 = runs2012;
	}
	public void addStudentToRun(int runNumber,String year,Student obj)
	{
		if (year.equals("2011"))
		{
			this.runs2011[runNumber].setStudentsList(obj);
		}
		else
		{
			this.runs2012[runNumber].setStudentsList(obj);
		}
	}
	
	public void computeTax()
	{
		//double runFee=0.0;
	}
	

	
	

}
