package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.core.Task;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.core.CAOService;
import com.oopca5.dao.RegisterDAO;

import java.util.Date;

public class RegisterCommand implements ICommand
{
    //add%%New Task%%John%%123456787
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        if(components.length == 4)
        {
            try
            {
                int caONumber = Integer.parseInt(components[1]);
                String dateOfBirth = components[2];
                String password =components[3];

                // Task newTask = new Task(taskName, taskOwner, new Date(deadline));
                Student newRegister = new Student(caONumber, dateOfBirth, password);

                RegisterDAO dao = new RegisterDAO();

                boolean added = dao.register(newRegister); //false;//taskList.add(newTask);
                if(added)
                {
                    response = CAOService.SUCCESSFUL_REGISTER;
                }
                else
                {
                    response = CAOService.FAILED_REGISTER;
                }
            }
            catch(NumberFormatException e)
            {
                response = CAOService.FAILED_REGISTER;
                System.out.println("Could not convert deadline to long " + e.getMessage());
            }
        }
        return response;
    }
}
