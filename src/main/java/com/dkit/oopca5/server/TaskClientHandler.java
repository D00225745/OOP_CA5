package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Task;
import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.core.CAOService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TaskClientHandler implements Runnable
{
    private Socket clientSocket;
    private TaskDatabase taskList;

    public TaskClientHandler(Socket clientSocket, TaskDatabase taskList)
    {
        this.clientSocket = clientSocket;

    }

    @Override
    public void run()
    {
        //Step 1: Open the lines of communication
        //Decorator pattern
        try
        {
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            //Instead of Step 1: clientOutput.println("Testing the socket")
            //           Step 2: clientOutput.flush();
            //autoFlush = true means we only need step 1
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            //Step 2: Set up repeated exchanges
            boolean sessionActive = true;
            while(sessionActive)
            {
                //Protocol logic
                //Note the line below is blocking - program execution waits here
                //until we get a request
                System.out.println("Waiting for client input");
                String request = clientInput.nextLine();
                System.out.println("Got input " + request);
                //The request will look like add%%New Task%%John%%67789897989
                //                           remove%%New Task
                //                           viewAll
                //                           exit

                // client tarafında gönderilen BREAKING_CHARACTER ile birliştirilmiş parametre metni
                // BREAKING_CHARACTER ile parçalara ayrılıyor
                String[] components = request.split(CAOService.BREAKING_CHARACTER);
                String response = null;

                ICommand command = CommandFactory.createCommand(components[0]);

                if (command != null)
                {
                    response = command.generateResponse(components, taskList);
                    if (response != null)
                    {
                        // Client ın gönderdiği request e karşılık server tarafında calışan ilgili command nesnesinin
                        // cevabı bu satrıda client a geri gönderilir.
                        clientOutput.println(response);

                        // Eğer kullanıcın seçtiği menu Exit veya quit is sunucu tarafındaki session sonlandırılıyor.
                        if (command instanceof ExitCommand)
                        {
                            sessionActive = false;
                        }
                    }
                }
            }

            clientSocket.close();
        }
        catch (IOException e)
        {
            System.out.println("Problem setting up communication channels " + e.getMessage());
        }
    }

}
