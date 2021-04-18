package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Task;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.core.CAOService;


import java.util.Date;

public class AddCommand implements ICommand
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
                String taskName = components[1];
                String taskOwner = components[2];
                long deadline = Long.parseLong(components[3]);

                Task newTask = new Task(taskName, taskOwner, new Date(deadline));
                boolean added = taskList.add(newTask);
                if(added)
                {
                    //response = TaskService.SUCCESSFUL_ADD;
                }
                else
                {
                    //  response = TaskService.FAILED_ADD;
                }
            }
            catch(NumberFormatException e)
            {
                // response = TaskService.FAILED_ADD;
                System.out.println("Could not convert deadline to long " + e.getMessage());
            }
        }
        return response;
    }
}
