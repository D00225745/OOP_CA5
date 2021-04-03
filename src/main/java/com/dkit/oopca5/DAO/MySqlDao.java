package com.dkit.oopca5.DAO;

import com.dkit.oopca5.Exceptions.DaoException;

import java.sql.DriverManager;

public class MySqlDao
{

    public Connection getConnection() throws DaoException
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

        catch (ClassNotFoundException exl)
        {

        }
    }
}
