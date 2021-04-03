package com.dkit.oopca5.BusinessObjects;

import com.dkit.oopca5.DAO.MySqlStudentDao;
import com.dkit.oopca5.DAO.StudentDaoInterface;
import com.dkit.oopca5.DTO.Student;
import com.dkit.oopca5.Exceptions.DaoException;

import java.util.List;

public class App {
    public static void main(String[] args) {

        StudentDaoInterface studentDao = new MySqlStudentDao();

        try {
            int caoNum = 55551111;
            String dob = "1999-01-22";
            String pw = "X3e4r5";
            studentDao.registerStudent(new Student(caoNum, dob, pw));

            Student result = studentDao.findStudent(55551111);
            System.out.println("Student::" + result);

            List<Student> studentList = studentDao.findAllStudents();

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

}
