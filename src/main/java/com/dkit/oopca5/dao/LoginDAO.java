package com.dkit.oopca5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkit.oopca5.core.Student;

public class LoginDAO extends MySqlDAO{

    public boolean login(Student register){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try
        {
            conn = this.getConnection();

            String query = " select * from  student where CAONumber = ? AND DateOfBirth = ? AND Password = ? ";


            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, register.getCaoNumber());
            preparedStmt.setString(2, register.getDateOfBirth());
            preparedStmt.setString(3, register.getPassword());

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();
            // girilen verilerle veritaban�nda kay�t varsa rs.next() true d�ner dolay�s�yla kay�t bulundu anlam� olur
            if (rs.next())
                result = true;
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            //throw new DaoException("findAllUsers() " + e.getMessage());
        }

        finally
        {
            try
            {
                if(rs != null)
                {
                    rs.close();
                }

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
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
