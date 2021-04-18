package com.dkit.oopca5.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dkit.oopca5.core.Student;



public class RegisterDAO extends MySqlDAO {
    public boolean register(Student register)
    {
        Connection conn = null;
        PreparedStatement ps = null;

        boolean result = false;
        try
        {
            conn = this.getConnection();

            // the mysql insert statement
            String query = " insert into student (CAONumber, DateOfBirth, Password)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, register.getCaoNumber());
            preparedStmt.setString(2, register.getDateOfBirth());
            preparedStmt.setString(3, register.getPassword());

            // execute the preparedstatement
            int rowEffected = preparedStmt.executeUpdate();

            if(rowEffected > 0)
                result = true;
        }

        catch (SQLException e)
        {
            //throw new DaoException("findAllUsers() " + e.getMessage());
        }

        finally
        {
            try
            {

                if(ps != null)
                {
                    ps.close();
                }

                if(conn != null)
                {
                    // freeConnection(con);
                }
            }

            catch (SQLException e)
            {

            }
        }
        return result;
    }

}
