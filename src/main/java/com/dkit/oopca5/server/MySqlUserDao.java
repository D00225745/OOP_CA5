package com.dkit.oopca5.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.exception.DaoException;


public class MySqlUserDao extends MySqlDAO //implements StudentDaoInterface
{
    @Override
    public List<Student> findAllStudents() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM STUDENT WHERE CAONUMBER =? AND PASSWORD =?";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while(rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String dob = rs.getString("dateOfBirth");
                String password = rs.getString("password");


                Student stu = new Student(caoNumber,dob, password);
                students.add(stu);
            }

        }

        catch (SQLException e)
        {
            throw new DaoException("findAllUsers() " + e.getMessage());
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

                if(con != null)
                {
                    // freeConnection(con);
                }
            }

            catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return students;

        //  return users;
    }

}

