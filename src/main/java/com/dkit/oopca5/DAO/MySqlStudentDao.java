package com.dkit.oopca5.DAO;

import com.dkit.oopca5.Exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlStudentDao extends MySqlDao implements StudentDaoInterface
{
    @Override
    public List<User> findAllUsers() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM USER";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while(rs.next())
            {
                int userId = rs.getInt("USER_ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                User u = new User(userId, firstname, lastname, username, password);
                users.add(u);
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
                    freeConnection(con);
                }
            }

            catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }

        return users;
    }





}
