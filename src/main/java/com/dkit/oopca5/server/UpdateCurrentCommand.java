package com.dkit.oopca5.server;


import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.dao.CourseDAO;
import com.dkit.oopca5.dao.RegisterDAO;

public class UpdateCurrentCommand implements ICommand {

    @Override
    public String generateResponse(String[] components, TaskDatabase taskList) {
        String response = null;
        if(components.length > 3)
        {
            try
            {
                int caoNumber = Integer.parseInt(components[1]);

                String[] choices = new String[components.length-2];

                for (int i = 2; i < components.length; i++) {
                    choices[i-2] = components[i];
                }


                CourseDAO dao = new CourseDAO();

                boolean updated = dao.updateChoices(caoNumber, choices);

                if(updated)
                {
                    response = CAOService.SUCCESSFUL_UPDATE_CURRENT;
                }
                else
                {
                    response = CAOService.FAILED_UPDATE_CURRENT;
                }
            }
            catch(NumberFormatException e)
            {
                response = CAOService.FAILED_UPDATE_CURRENT;
                System.out.println("Could not convert deadline to long " + e.getMessage());
            }
        }
        return response;
    }

}
