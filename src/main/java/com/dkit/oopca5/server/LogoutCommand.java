package com.dkit.oopca5.server;


import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.TaskDatabase;

public class LogoutCommand implements ICommand
{
    //add%%New Task%%John%%123456787
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        return CAOService.LOGGED_OUT;
    }
}