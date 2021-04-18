package com.dkit.oopca5.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class MySqlDAO
{
    public Connection getConnection() throws SQLException
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oop_ca5_berk_tatar";
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


}
