package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

//Berk Tatar D00225745


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CAOService
{
    public static final String BREAKING_CHARACTER = "%%";
    public static final int PORT_NUM = 50007;
    public static final String HOSTNAME = "localhost";

    public static final String TASK_SEPARATOR = "##";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";

    public static final String LOGIN_COMMAND = "LOGIN";
    public static final String LOGGED_IN = "LOGGED IN";
    public static final String LOGIN_FAILED = "LOGIN FAILED";

    public static final String LOGOUT_COMMAND = "LOGOUT";
    public static final String LOGGED_OUT = "LOGGED OUT";

    public static final String DISPLAY_COURSE_COMMAND = "DISPLAY COURSE";
    public static final String DISPLAY_FAILED = "DISPLAY FAILED";

    public static final String DISPLAY_ALL_COMMAND = "DISPLAY_ALL";
    public static final String FAILED_DISPLAY_ALL = "FAILED DISPLAY_ALL";
    public static final String SUCCESSFUL_DISPLAY_ALL = "SUCCESSFUL DISPLAY ALL";

    public static final String DISPLAY_CURRENT_COMMAND = "DISPLAY CURRENT";
    public static final String FAILED_DISPLAY_CURRENT = "FAILED DISPLAY CURRENT";
    public static final String SUCCESSFUL_DISPLAY_CURRENT = "SUCCESSFUL DISPLAY CURRENT";

    public static final String UPDATE_CURRENT_COMMAND = "UPDATE CURRENT";
    public static final String FAILED_UPDATE_CURRENT = "FAILED UPDATE CURRENT";
    public static final String SUCCESSFUL_UPDATE_CURRENT = "SUCCESSFUL UPDATE CURRENT";

//    public static final String ADD_COMMAND = "add";
//    public static final String SUCCESSFUL_ADD = "ADDED";
//    public static final String FAILED_ADD = "FAILED";
//
//    public static final String REMOVE_COMMAND = "remove";
//    public static final String SUCCESSFUL_REMOVE = "DELETED";
//    public static final String FAILED_REMOVE = "NOT_FOUND";
//
//    public static final String VIEW_COMMAND = "viewAll";
//    public static final String TASK_SEPARATOR = "##";
//
//    public static final String EXIT_COMMAND = "exit";
//    public static final String SIGN_OFF = "GOODBYE";
//
//    public static final String TRYING_TO_HACK = "STOP TRYING TO HACK US";

    //Finish correcting ca5 del one, John, 88888888888
    //Read masters thesis, John, 999999999999
    //Finish correcting ca5 del one%%John%%8888888888##Read masters thesis%%John%%9999999999

    public static String flattenTaskList(List<Task> taskList)
    {
        if(!taskList.isEmpty())
        {
            String tasks = taskList.get(0).format();
            for(int i=1; i < taskList.size(); i++)
            {
                tasks = tasks + CAOService.TASK_SEPARATOR + taskList.get(i).format();
            }
            return tasks;
        }
        else
        {
            return null;
        }
    }

    //This is the opposite of the previous method
    //takes input that looks like Finish correcting ca5 del one%%John%%8888888888##Read masters thesis%%John%%9999999999
    public static List<Task> recreateTaskList(String tasks)
    {
        List<Task> taskList = new ArrayList<>();
        //Now taskStrings will contain - Finish correcting ca5 del one%%John%%8888888888 and Read masters thesis%%John%%9999999999
        String[] taskStrings = tasks.split(CAOService.TASK_SEPARATOR);
        for (String task : taskStrings)
        {
            //Finish correcting ca5 del one%%John%%8888888888
            //components - Finish correcting ca5 del one, John,8888888888
            String[] components = task.split(CAOService.BREAKING_CHARACTER);
            if (components.length == 3)
            {
                try
                {
                    long deadlineTime = Long.parseLong(components[2]);
                    Date deadline = new Date(deadlineTime);
                    Task t = new Task(components[0], components[1], deadline);
                    taskList.add(t);
                } catch (NumberFormatException e)
                {
                    System.out.println("Deadline information is not a long. Skipping entry");
                }
            }
        }
        return taskList;
    }
}
