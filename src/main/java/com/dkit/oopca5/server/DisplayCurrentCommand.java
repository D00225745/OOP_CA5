package com.dkit.oopca5.server;


import java.util.List;

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.dao.CourseDAO;

public class DisplayCurrentCommand implements ICommand {

    @Override
    public String generateResponse(String[] components, TaskDatabase taskList) {
        String response = null;
        // Client tarafından gönderilen parametre sayısıyla uyumlu olup olmadığını kontrol eder
        if(components.length == 2)
        {
            try
            {
                int caoNumber = Integer.parseInt(components[1]);

                // Veri tabanına bağlanacak dao nesnesi oluşturuluyor
                CourseDAO dao = new CourseDAO();
                // veritabanına login metoduyla bağlandıktan sonra kullanıcı login olabildiyse true olmadıysa false cevabı alınır
                List<String> courseList = dao.getCurrentCourses(caoNumber);

                if(courseList.size() == 0)
                {
                    response = CAOService.FAILED_DISPLAY_ALL;
                }
                else
                {
                    response = CAOService.SUCCESSFUL_DISPLAY_CURRENT ;
                    for (String courseId : courseList) {
                        response +=  CAOService.BREAKING_CHARACTER + courseId ;
                    }

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
