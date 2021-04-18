package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Task;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.core.CAOService;

import java.util.Date;
import java.util.List;

public class ViewCommand implements ICommand
{
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response =  null;
        if(components.length == 1)
        {
            List<Task> tasks = taskList.getAllTasks();
            response = CAOService.flattenTaskList(tasks);
            if(response == null)
            {
                response = "DummyTask%%No Owner%%"+new Date().getTime();
            }
        }
        return response;
    }
}
