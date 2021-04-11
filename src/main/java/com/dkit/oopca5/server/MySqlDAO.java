package com.dkit.oopca5.server;

/*
All of the database functionality should be here. You will need a DAO for each table that you are interacting with in the database
 */

//Berk Tatar D00225745

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.exception.DaoException;

public class MySqlDAO
{
    public Connection getConnection() throws SQLException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oop_ca5_berk_tatar_emmanuel_francis";
        String username = "root";
        String password = "";
        Connection con = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }

        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class not found " +cnfe.getMessage());
        }

        System.out.println("Connected Succesfully");
        return con;
    }

    public List<Student> findAllStudents() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }
}
