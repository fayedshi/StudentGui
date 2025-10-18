package com.tuition.model;

import java.util.Objects;

public class Enrollment {
    private String courseName;
    private String grade;

    @Override
    public String toString() {
        return courseName + "," + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseName);
    }

    public Enrollment(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void listEnrollments() {
        System.out.println("Course Name: " + this.courseName + "\t Grade: " + this.grade);
    }

}
