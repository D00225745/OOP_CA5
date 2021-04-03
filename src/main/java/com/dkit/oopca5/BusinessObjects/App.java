package com.dkit.oopca5.BusinessObjects;

import com.dkit.oopca5.DAO.MySqlStudentDao;
import com.dkit.oopca5.DAO.StudentDaoInterface;
import com.dkit.oopca5.DTO.Student;
import com.dkit.oopca5.Exceptions.DaoException;

public class App
{
    public static void main(String[] args)
    {
        // load students
        StudentManager studentManager = new StudentManager();

       // Student s = studentManager.getStudent(12345678);

        int caoNum = 22223333;
        String dob = "1999-11-26";
        String pw = "w3e4r5";
        studentManager.addStudent(new Student(caoNum,dob,pw));

        studentManager.addStudent(expected);

        Student actual = studentManager.getStudent(22223333);

        assertTrue(actual.equals(expected));

    }
}
