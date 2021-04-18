package com.dkit.oopca5.server;

import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.core.CAOService;

public class ExitCommand implements ICommand
{
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        if(components.length == 1)
        {
            response = CAOService.LOGGED_OUT;
        }
        return response;
    }
}
