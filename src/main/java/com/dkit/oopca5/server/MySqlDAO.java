package com.dkit.oopca5.server;

/*
All of the database functionality should be here. You will need a DAO for each table that you are interacting with in the database
 */

//Berk Tatar D00225745 and Emmanuel Francis D00228281

import java.sql.DriverManager;

public class MySqlDAO
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
