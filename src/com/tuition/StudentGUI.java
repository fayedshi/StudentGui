package com.tuition;

import com.tuition.model.Enrollment;
import com.tuition.model.Staff;
import com.tuition.model.Student;
import com.tuition.service.StaffAdmin;
import com.tuition.service.StudentAdmin;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StudentGUI extends JFrame implements ActionListener {
    /* controle definition */
    private final JTextField txtStuId, txtName, txtDOB;
    private JLabel lbl_title;
    //    private JLabel lbl_course;
    private final JLabel lblStatMsg;
    private final JFileChooser fc = new JFileChooser();
    private final String stuFileName = "studentList.txt";
    private String courseFileName = "courseInfo.txt";
    private String stfFileName = "staffList.txt";
    JPanel jpDisp = new JPanel(); // for display the content and manipulations
    JPanel jpMenu = new JPanel(); // menus stayed right side, as add student,
    JButton btnMain = new JButton("Main Menu");
    JButton btnStuAdm = new JButton("Student Admin");
    JButton btnCursAdm = new JButton("Course Admin");
    JButton btnStfAdm = new JButton("Staff Admin");
    JButton btnExit = new JButton("Exit");
    JPanel jpBottom = new JPanel();
    JComboBox<String> courseBox = new JComboBox<>();
    JList<String> jListEnrolled = new JList<>();
    Container container = getContentPane();
    StudentAdmin studentAdmin = new StudentAdmin();

    /* data definition */
    final String[] courses = {"Java", "OO design", "Software Testing", "J2EE", "Software Architecture", "Design Patterns"};
    List<Student> students = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();

    public StudentGUI() {
        txtStuId = new JTextField(5);
        txtName = new JTextField(5);
        txtDOB = new JTextField(5);

        container.setLayout(new BorderLayout(4, 2));
        btnMain.addActionListener(this);
        // student menu
        btnStuAdm.addActionListener(this);
        jpMenu.add(btnStuAdm);
        // course
        // JButton btnCursAdm = new JButton("Course Admin");
        btnCursAdm.addActionListener(this);
        jpMenu.add(btnCursAdm);
        // staff
        btnStfAdm.addActionListener(this);
        jpMenu.add(btnStfAdm);

        btnExit.addActionListener(e -> System.exit(0));
        jpMenu.add(btnExit);
        jpMenu.setBounds(330, 0, 80, 200);
        jpMenu.setBackground(Color.GRAY);
        jpMenu.setLayout(new GridLayout(4, 1));

        // panel of status of operations
        lblStatMsg = new JLabel("status");
        lblStatMsg.setForeground(Color.green);
        jpBottom.add(this.lblStatMsg);
        jpBottom.setBackground(Color.WHITE);
        jpDisp.setBackground(Color.lightGray);

        container.add(jpDisp, BorderLayout.CENTER);
        container.add(jpBottom, BorderLayout.SOUTH);
        container.add(jpMenu, BorderLayout.EAST);
        this.LoadFileData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Main Menu")) {
            showMainMenu();
        } else if (cmd.equals("Student Admin")) {
            showStuAdminMenu();
        } else if (cmd.equals("List Students")) {
            listStudents();
        } else if (cmd.equals("Add Student")) {
            showAddStuMenu();
        } else if (cmd.equals("Submit")) {
            addStudent();
        } else if (cmd.equals("Save Students")) {
            saveList(students);
        } else if (cmd.equals("Save Staff")) {
            saveList(staffList);
        } else if (cmd.equals("Course Admin")) {
            showCourseAdminMenu();
        } else if (cmd.equals("Students On Course")) {
            listCourseStu();
        } else if (cmd.equals("Students Enrolled")) {
            showEnrolledStudents();
        } else if (cmd.equals("Staff Admin")) {
            staffAdminMenu();
        } else if (cmd.equals("List Instructors")) {
            listStaff();
        }
    }

    public void showMainMenu() {
        jpMenu.removeAll();
        jpMenu.setVisible(false);
        jpMenu.add(btnStuAdm);
        jpMenu.add(btnCursAdm);
        jpMenu.add(btnStfAdm);
        jpMenu.add(btnExit);
        jpDisp.removeAll();
        jpMenu.setVisible(true);
    }

    // student Admin menu
    void showStuAdminMenu() {
        jpMenu.removeAll();
        jpMenu.setVisible(false);
        JButton btnLoadStu = new JButton("List Students");
        btnLoadStu.addActionListener(this);
        jpMenu.add(btnLoadStu);
        JButton btnAddStuMenu = new JButton("Add Student");
        btnAddStuMenu.addActionListener(this);
        jpMenu.add(btnAddStuMenu);
        jpMenu.add(btnMain);
        jpMenu.setVisible(true);

    }

    // course admin menu
    void showCourseAdminMenu() {
        // course Admin menu
        jpMenu.removeAll();
        jpMenu.setVisible(false);
        JButton btnListCseStu = new JButton("Students On Course");
        btnListCseStu.addActionListener(this);
        jpMenu.add(btnListCseStu);
        jpMenu.add(btnMain);
        jpMenu.setVisible(true);

    }

    // staff admin
    void staffAdminMenu() {
        jpMenu.removeAll();
        jpMenu.setVisible(false);
        JButton btnList = new JButton("List Instructors");
        btnList.addActionListener(this);
        jpMenu.add(btnList);
        jpMenu.add(btnMain);
        jpMenu.setVisible(true);
    }

    void listStaff() {
        jpDisp.removeAll();
        jpDisp.setVisible(false);
        fc.setSelectedFile(new File(stfFileName));
        jpDisp.setLayout(new FlowLayout());
        JButton btnSave;
        jpDisp.add(new JList<>(staffList.toArray()));
        btnSave = new JButton("Save Staff");
        btnSave.addActionListener(this);
        jpDisp.add(btnSave);
        jpDisp.setVisible(true);
    }

    void listCourseStu() {
        jpDisp.removeAll();
        jpDisp.setVisible(false);
        fc.setSelectedFile(new File(stuFileName));
//        jpDisp.setLayout(new BoxLayout(jpDisp,BoxLayout.X_AXIS));
        for (String course : courses) {
            courseBox.addItem(course);
        }

        JPanel sportPanel = new JPanel();
        sportPanel.setLayout(new BoxLayout(sportPanel, BoxLayout.Y_AXIS));// 垂直布局
//        sportPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        sportPanel.add(courseBox);
        sportPanel.add(jListEnrolled);

        jpDisp.add(new Label("Select a course:"));
        JButton btnShowEnrolled = new JButton("Students Enrolled");
        btnShowEnrolled.addActionListener(StudentGUI.this);
        jpDisp.add(sportPanel);
        jpDisp.add(btnShowEnrolled);
        jpDisp.setVisible(true);
    }

    void listStudents() {
        jpDisp.setVisible(false);
        jpDisp.removeAll();
        jpDisp.setLayout(new GridLayout(6, 4));
        fc.setSelectedFile(new File(stuFileName));
        JButton btnSave = new JButton("Save Students");
        btnSave.addActionListener(this);
        for (Student student : students) {
            JLabel lblStuName = new JLabel();
            lblStuName.setText(student.getStudName());
            JComboBox<String> combList = studentAdmin.popCombCourses(student);
            final JTextField txtFee = new JTextField();
            JButton btnEnroll = getjButton(student, combList);
            JButton btnFeeUpd = getjButton(student, txtFee);
            JLabel lblFee = new JLabel("Fees Paid");
            txtFee.setText("$" + student.getFeePaid());
            JPanel jp = new JPanel();
            jp.setLayout(new FlowLayout(FlowLayout.LEFT));
            jp.add(lblStuName);
            jp.add(combList);
            jp.add(btnEnroll);
            jp.add(lblFee);
            jp.add(txtFee);
            jp.add(btnFeeUpd);
            jp.setBorder(null);
            jpDisp.add(jp);
        }
        jpDisp.add(btnSave);
        jpDisp.setVisible(true);
        System.out.println("Students information refreshed.");
    }

    private JButton getjButton(Student student, JTextField txtFee) {
        JButton btnFeeUpd = new JButton("Update Fee");
        btnFeeUpd.addActionListener(e -> {
            try {
                try {
                    student.setFeePaid(Double.parseDouble(txtFee.getText().substring(1)));
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(StudentGUI.this, "Invalid Fee value, format is like $999");
                    txtFee.requestFocus();
                    return;
                }
                saveToFile(stuFileName, students, false);
                lblStatMsg.setText("Fees Paid is updated.");
            } catch (IOException e1) {
            }
        });
        return btnFeeUpd;
    }

    private JButton getjButton(Student student, JComboBox<String> jCourseList) {
        JButton btnEnroll = new JButton("Enroll");
        if (jCourseList.getItemAt(0) == null) {
            btnEnroll.setEnabled(false);
        }
        btnEnroll.addActionListener(e -> {
            try {
                // overwrite old file
                String csName = jCourseList.getSelectedItem().toString();
                student.enroll(csName, "Run 1", -1); // give it 'Run 1' when enrolling
                saveToFile(stuFileName, students, false);
                lblStatMsg.setText("Course " + csName + " is enrolled.");
                jCourseList.removeItem(jCourseList.getSelectedItem());
                if (jCourseList.getItemAt(0) == null) {
                    btnEnroll.setEnabled(false);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        return btnEnroll;
    }

    void addStudent() {
        // System.out.println("Id:" + txt_id.getText());
        if (txtStuId.getText().trim().equals("") || getStudentById(txtStuId.getText()) != null) {
            JOptionPane.showMessageDialog(StudentGUI.this, "Invalid Student ID.");
            txtStuId.requestFocus();
            return;
        }
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(StudentGUI.this, "Name can not be empty.");
            return;
        }
        if (!txtDOB.getText().trim().matches("^\\d+$")) {
            JOptionPane.showMessageDialog(StudentGUI.this, "DOB should be numbers.");
            return;
        }
        Student student = new Student(txtStuId.getText(), txtName.getText(), Integer.parseInt(txtDOB.getText()));
        try {
            // filewrite to specify 2nd arg to true for appending
            for (String c : courses) {
                student.enroll(c, "-", -1); // new student doesn't enroll any course
            }
            student.setFeePaid(0);
            students.add(student);
            saveToFile(stuFileName, List.of(student), true);
            lblStatMsg.setText("One Student Added.");
        } catch (Exception ie) {
            throw new RuntimeException(ie.getMessage());
        }

    }

    void showAddStuMenu() {
        jpDisp.removeAll();
        jpDisp.setVisible(false);
        JLabel lblName = new JLabel("Student Name: ");
        JLabel lblStuId = new JLabel("Student ID: ");
        JLabel lblDOB = new JLabel("Birthday: ");
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
//        jpDisp.setBounds(300,500,160,160);
        centerPanel.add(lblStuId);
        centerPanel.add(txtStuId);
        centerPanel.add(lblName);
        centerPanel.add(txtName);
        centerPanel.add(lblDOB);
        centerPanel.add(txtDOB);
        JButton btnAddStu = new JButton("Submit");
        btnAddStu.addActionListener(this);
        centerPanel.add(btnAddStu);
        jpDisp.add(centerPanel);
        jpDisp.setVisible(true);
    }

    // save objects to file
    void saveList(List<?> objList) {
        int rVal = fc.showSaveDialog(StudentGUI.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String outfile = fc.getSelectedFile().getAbsolutePath();
            System.out.println("file selected: " + outfile);
            try {
                saveToFile(outfile, objList, false);
                lblStatMsg.setText("File saved as: " + outfile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    void saveToFile(String fileName, List<?> objects, boolean append) throws IOException {
        FileWriter writer = new FileWriter(fileName, append);
        for (Object o : objects) {
            writer.write(o.toString());
        }
        writer.close();
    }

    /* get available courses */
//    private void prepCourse(JComboBox list, Student st) {
//        for (Enrollment enr : st.getEnrollmentList()) {
//            try {
//                // System.out.println("grade " + enr.getGrade());
//                if (enr.getGrade().equals("-")
//                        || Integer.parseInt(enr.getGrade()) < 50) {
//                    list.addItem(enr.getCourseName());
//                }
//            } catch (Exception e) {
//            }
//        }
//    }

    void showEnrolledStudents() {
        List<String> strEnrolls = new ArrayList<>();
        for (Student student : students) {
            Enrollment en = student.searchEnrollment(courseBox.getSelectedItem().toString());
            if (en != null && !en.getGrade().equalsIgnoreCase("Exp") && !en.getGrade().equals("-")) {
                strEnrolls.add(student.getStuId() + ", " + en.getGrade());
            }
        }
        System.out.println("enrolled students: " + strEnrolls);
        jListEnrolled.setListData(strEnrolls.toArray(new String[0]));
    }

    // load students data on start up
    public void LoadFileData() {
        try {
            students = studentAdmin.loadStudentsFromFile(stuFileName, courses);
            staffList = StaffAdmin.loadStaff(stfFileName);
        } catch (Exception ie) {
            System.err.println(ie.getMessage());
        }
        System.out.println("students are imported from file." + students);
    }

    public Student getStudentById(String id) {
        for (Student s : students)
            if (s.getStuId().equalsIgnoreCase(id)) return s;
        return null;
    }

    public static void main(String[] args) {
        StudentGUI stud = new StudentGUI();
        stud.setVisible(true);
        stud.setTitle("Student Tuition Center");
        stud.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        stud.setSize(new Dimension(1200, 500));
    }
}