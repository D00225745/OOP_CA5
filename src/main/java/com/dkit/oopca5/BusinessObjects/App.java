package com.dkit.oopca5.BusinessObjects;

import com.dkit.oopca5.DAO.MySqlStudentDao;
import com.dkit.oopca5.DAO.StudentDaoInterface;
import com.dkit.oopca5.Exceptions.DaoException;

public class App
{
    public static void main(String[] args)
    {
        StudentDaoInterface studentDao = new MySqlStudentDao();

        try
        {
            List<Student> studentList = studentDao.findAllStudents();
            System.out.println(studentList);
        }
        catch(DaoException e)
        {
            e.printStackTrace();
        }
    }
}
