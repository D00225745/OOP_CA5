package com.dkit.oopca5.server;

/*
All of the database functionality should be here. You will need a DAO for each table that you are interacting with in the database
 */

import java.sql.DriverManager;

public class MySqlDAO
{
    public Connection getConnection() throws DAOException
    {
        String driver = "com.mysql.cj.jbdc.Driver";
        String url = "jbdc:mysql://localhost:3306/OOP_CA5_Berk_Tatar_Emmanuel_Francis";
        String username = "root";
        String password = "";

        Connection con = null;
    }

    try
    {
        Class.forName(driver);
        con = DriverManager.getConnection(url, username, password)
    }
}
