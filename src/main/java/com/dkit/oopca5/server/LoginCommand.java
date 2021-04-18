package com.dkit.oopca5.server;


import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.server.ICommand;
import com.dkit.oopca5.dao.LoginDAO;
import com.dkit.oopca5.dao.RegisterDAO;

public class LoginCommand implements ICommand
{
    //add%%New Task%%John%%123456787
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        // Client tarafından gönderilen parametre sayısıyla uyumlu olup olmadığını kontrol eder
        if(components.length == 4)
        {
            try
            {
                int caONumber = Integer.parseInt(components[1]);
                String dateOfBirth = components[2];
                String password =components[3];

                // Task newTask = new Task(taskName, taskOwner, new Date(deadline));
                Student register = new Student(caONumber, dateOfBirth, password);
                // Veri tabanına bağlanacak dao nesnesi oluşturuluyor
                LoginDAO dao = new LoginDAO();
                // veritabanına login metoduyla bağlandıktan sonra kullanıcı login olabildiyse true olmadıysa false cevabı alınır
                boolean loggeIn = dao.login(register); //false;//taskList.add(newTask);
                if(loggeIn)
                {
                    response = CAOService.LOGGED_IN;
                }
                else
                {
                    response = CAOService.LOGIN_FAILED;
                }
            }
            catch(NumberFormatException e)
            {
                response = CAOService.LOGIN_FAILED;
                System.out.println("Could not convert CAONumber to int " + e.getMessage());
            }
        }
        return response;
    }
}
