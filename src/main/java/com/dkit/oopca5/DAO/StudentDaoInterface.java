package com.dkit.oopca5.DAO;

import com.dkit.oopca5.Exceptions.DaoException;

public class StudentDaoInterface
{


    public List<Student> findAllStudents() throws DaoException;
}
