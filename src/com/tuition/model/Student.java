package com.tuition.model;

import java.util.*;

public class Student {
    /**
     *
     */
    private String stuId, studName;
    private int DOB;
    private List<Enrollment> enrollmentList = new ArrayList<>(20);
    private Set<String> avaCourses;
    private double totalFee = 0.0, feePaid = 0.0;
    private boolean instructor = false;
    private Map<String, Enrollment> enrollmentMap = new HashMap<>();

    public Student(String stuId, String studName, int DOB) {
        this.stuId = stuId;
        this.studName = studName;
        this.DOB = DOB;
    }// end of the constructor block

    public String toString() {
        StringBuffer sb = new StringBuffer(stuId + "," + studName + "," + DOB);
        for (Enrollment enr : enrollmentList) {
            sb.append(",").append(enr.getGrade());
        }
        sb.append(",$").append(getFeePaid()).append("\n");
        return sb.toString();
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public int getDOB() {
        return DOB;
    }

    public void setDOB(int dOB) {
        DOB = dOB;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(double feePaid) {
        this.feePaid = feePaid;
    }

    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

    public void exemptStudent() throws Handler {

    }// end of the exemptStudent Method

    public Set<String> getAvaCourses() {
        return avaCourses;
    }

    public void setAvaCourses(Set<String> avaCourses) {
        this.avaCourses = avaCourses;
    }

    public void enroll(String courseName, String grade, int run) throws Handler {
        Enrollment objRef;
        objRef = searchEnrollment(courseName);
        if (objRef == null) {
            objRef = new Enrollment(courseName, grade);
            enrollmentList.add(objRef);
            enrollmentMap.put(courseName, objRef);
        } // valid enrollment
        else {
            String oldGrade = objRef.getGrade();
            if (oldGrade.equals("-")) {
//                objRef = new Enrollment(courseName, tempGrade);
                objRef.setGrade(grade);
                System.out.println(enrollmentList);
//                enrollmentList.add(objRef);
            } else if (oldGrade.equals("Exp")) {
                throw new Handler("The Student Has Been Exempted From this Course");
            } // end of the if block
            else if (Integer.parseInt(oldGrade) > 50) {
                throw new Handler("The Student is already Enrolled is This Course");
            } else {
                objRef.setGrade(grade);
                System.out.println(enrollmentList);
            }

        } // end of else block
    }// end of enroll block

    public void calculateTotalFee() {

    }// end of calculateTotalFee block

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    public Enrollment searchEnrollment(String courseName) {
//        Enrollment tempObj;
//        Iterator<Enrollment> i = this.enrollmentList.iterator();
//        while (i.hasNext()) {
//            tempObj = i.next();
//            if (tempObj.getCourseName().equalsIgnoreCase(courseName))
//                return tempObj;
//        } // end of the while block
//        Enrollment en = new Enrollment(courseName, "0");
        return enrollmentMap.get(courseName);
    }// end of the helper method

    public List<Enrollment> getEnrollmentList() {
        return this.enrollmentList;
    }

    public Map<String, Enrollment> getEnrollmentMap() {
        return enrollmentMap;
    }

    public void setEnrollmentMap(Map<String, Enrollment> enrollmentMap) {
        this.enrollmentMap = enrollmentMap;
    }
}
