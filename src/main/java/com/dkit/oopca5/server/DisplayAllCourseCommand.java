package com.dkit.oopca5.server;

import java.util.List;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.dao.CourseDAO;
import com.dkit.oopca5.dao.LoginDAO;

public class DisplayAllCourseCommand implements ICommand
{
    //add%%New Task%%John%%123456787
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        // Client tarafından gönderilen parametre sayısıyla uyumlu olup olmadığını kontrol eder
        if(components.length == 1)
        {
            try
            {

                // Veri tabanına bağlanacak dao nesnesi oluşturuluyor
                CourseDAO dao = new CourseDAO();
                List<Course> courses = dao.getAllCourses();

                if(courses == null)
                {
                    response = CAOService.FAILED_DISPLAY_ALL;
                }
                else
                {
                    response = CAOService.SUCCESSFUL_DISPLAY_ALL ;

                    for (int i = 0; i < courses.size(); i++) {

                        Course course = courses.get(i);

                        response += CAOService.BREAKING_CHARACTER + course.getCourseid() +
                                CAOService.BREAKING_CHARACTER +
                                course.getLevel() +  CAOService.BREAKING_CHARACTER +
                                course.getCourseTitle() +  CAOService.BREAKING_CHARACTER +
                                course.getInstitution() ;

                    }

                }
            }
            catch(Exception e)
            {
                response = CAOService.FAILED_DISPLAY_ALL;
                System.out.println( e.getMessage());
            }
        }
        return response;
    }
}

