package com.dkit.oopca5.server;


import java.util.List;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.dao.CourseDAO;
import com.oopca5.dao.LoginDAO;

public class DisplayCourseCommand implements ICommand
{
    //add%%New Task%%John%%123456787
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        // Client tarafından gönderilen parametre sayısıyla uyumlu olup olmadığını kontrol eder
        if(components.length == 2)
        {
            try
            {
                String courseid = components[1];

                // Veri tabanına bağlanacak dao nesnesi oluşturuluyor
                CourseDAO dao = new CourseDAO();
                // veritabanına login metoduyla bağlandıktan sonra kullanıcı login olabildiyse true olmadıysa false cevabı alınır
                Course course = dao.getCourse(courseid);

                if(course == null)
                {
                    response = CAOService.FAILED_DISPLAY_ALL;
                }
                else
                {

                    response = course.getCourseid() + CAOService.BREAKING_CHARACTER +
                            course.getLevel() +  CAOService.BREAKING_CHARACTER +
                            course.getCourseTitle() +  CAOService.BREAKING_CHARACTER +
                            course.getInstitution() ;
                }
            }
            catch(Exception e)
            {
                response = CAOService.DISPLAY_FAILED;
                System.out.println( e.getMessage());
            }
        }
        return response;
    }
}

