package com.tuition.service;

import com.tuition.model.Enrollment;
import com.tuition.model.Student;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class StudentAdmin {

    public List<Student> loadStudentsFromFile(String stuFileName, String[] courses) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(stuFileName));
        String line;
        String[] fields;
        List<Student> studentList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            fields = line.split(",");
            // set enrollments for taken courses
            Student student = new Student(fields[0], fields[1], Integer.parseInt(fields[2]));
            List<Enrollment> enrollmentList = new ArrayList<>(); // full set of course actually
            Map<String, Enrollment> enrollmentMap = new HashMap<>();
            Set<String> avaCourses = new HashSet<>();
            for (int i = 0; i < courses.length; i++) {
                String grade = fields[i + 3];
                if (grade.equals("-")) {
                    avaCourses.add(courses[i]);
                }
//                if (grade.equalsIgnoreCase("Exp")) {
//                    continue;
//                }
//                try {
//                    if (Integer.parseInt(grade) < 50) {
//                        avaCourses.add(courses[i]);
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Number format exception ignored: " + e.getMessage());
//                }
                Enrollment en = new Enrollment(courses[i], grade);
                enrollmentList.add(en);
                enrollmentMap.put(courses[i], en);
            }
            student.setFeePaid(Double.parseDouble(fields[9].substring(1)));
            student.setEnrollmentList(enrollmentList);
            student.setEnrollmentMap(enrollmentMap);
            student.setAvaCourses(avaCourses);
            studentList.add(student);
        }
        br.close();
        return studentList;
    }

    /* get available courses */
//    public JComboBox<String> getAvaCourses(Student st) {
//        JComboBox<String> jComboBox = new JComboBox<>();
//        for (String course : st.getAvaCourses()) {
//            jComboBox.addItem(course);
//        }
//        return jComboBox;
//    }

    // populate comboBox with available courses
    public JComboBox<String> popCombCourses(Student student) {
        JComboBox<String> jComboBox = new JComboBox<>();
        for (Enrollment er : student.getEnrollmentList()) {
            try {
                if (er.getGrade().equals("-") || Integer.parseInt(er.getGrade()) < 50) {
                    jComboBox.addItem(er.getCourseName());
                }
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return jComboBox;
    }
}
