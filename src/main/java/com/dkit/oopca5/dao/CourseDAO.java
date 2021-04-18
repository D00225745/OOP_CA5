package com.dkit.oopca5.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;

public class CourseDAO extends MySqlDAO{

    public Course getCourse(String courseid){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        Course course = null;

        try
        {
            conn = this.getConnection();

            String query = " select courseid, level, title, institution from  course where courseid = ? ";


            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, courseid);


            // execute the preparedstatement
            rs = preparedStmt.executeQuery();
            // girilen verilerle veritaban�nda kay�t varsa rs.next() true d�ner dolay�s�yla kay�t bulundu anlam� olur
            if (rs.next()) {
                course = new Course();
                course.setCourseid(rs.getString(1));
                course.setLevel(rs.getInt(2));
                course.setCourseTitle(rs.getString(3));
                course.setInstitution(rs.getString(4));

            }

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
        return course;
    }

    public List<Course> getAllCourses() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        List<Course> courses = new ArrayList<Course>();

        try
        {
            conn = this.getConnection();

            String query = " select courseid, level, title, institution from  course ";


            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);


            // execute the preparedstatement
            rs = preparedStmt.executeQuery();
            // girilen verilerle veritaban�nda kay�t varsa rs.next() true d�ner dolay�s�yla kay�t bulundu anlam� olur
            while (rs.next()) {
                Course course = new Course();
                course.setCourseid(rs.getString(1));
                course.setLevel(rs.getInt(2));
                course.setCourseTitle(rs.getString(3));
                course.setInstitution(rs.getString(4));

                courses.add(course);
            }

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
        return courses;
    }

    public List<String> getCurrentCourses(int caoNumber) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        List<String> courseList = null;

        try
        {
            conn = this.getConnection();

            String query = " select courseid from  student_courses where caonumber = ? ";


            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, caoNumber);


            // execute the preparedstatement
            rs = preparedStmt.executeQuery();
            courseList = new ArrayList<>();
            // girilen verilerle veritaban�nda kay�t varsa rs.next() true d�ner dolay�s�yla kay�t bulundu anlam� olur
            while (rs.next()) {

                courseList.add(rs.getString(1));

            }

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
        return courseList;
    }

    public boolean updateChoices(int caoNumber, String[] choices) {
        Connection conn = null;
        PreparedStatement ps = null;

        boolean result = false;
        try
        {
            conn = this.getConnection();

            // the mysql insert statement
            String query = " update student_courses set courseid = ?  where caoNumber = ? and courseid = ?";

            // create the mysql insert preparedstatement

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            for (int i = 0; i < choices.length; i++) {
                preparedStmt.setString(1, choices[i]);
                preparedStmt.setInt (2, caoNumber);
                preparedStmt.setString(3, choices[i]);
            }

            // execute the preparedstatement
            int[] rowEffected = preparedStmt.executeBatch();

            if(rowEffected.length > 0)
                result = true;
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
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
