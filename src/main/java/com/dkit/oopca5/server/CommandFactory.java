package com.dkit.oopca5.server;

import com.dkit.oopca5.core.CAOService;
//Demonstrates the Factory pattern - this factory creates a command of the requested type

public class CommandFactory
{
    public static ICommand createCommand(String requestCommand)
    {
        ICommand c = null;
        switch(requestCommand)
        {
            case CAOService.REGISTER_COMMAND:
                c = new RegisterCommand();
                break;
            case CAOService.LOGIN_COMMAND:
                c = new LoginCommand();
                break;
            case CAOService.LOGGED_OUT:
                c = new LogoutCommand();
                break;
            case CAOService.DISPLAY_COURSE_COMMAND:
                c = new DisplayCourseCommand();
                break;
            case CAOService.DISPLAY_ALL_COMMAND:
                c = new DisplayAllCourseCommand();
                break;
            case CAOService.DISPLAY_CURRENT_COMMAND:
                c = new DisplayCurrentCommand();
                break;
            case CAOService.UPDATE_CURRENT_COMMAND:
                c = new UpdateCurrentCommand();
                break;

        }
        return c;
    }
}