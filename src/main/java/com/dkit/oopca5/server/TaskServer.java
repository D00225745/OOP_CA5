package com.dkit.oopca5.server;


import com.dkit.oopca5.core.TaskDatabase;
import com.dkit.oopca5.core.CAOService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer
{
    public static void main(String[] args)
    {
        TaskDatabase taskList = new TaskDatabase();

        try
        {
            ServerSocket connectionSocket = new ServerSocket(CAOService.PORT_NUM);
            while(true)
            {
                // Client dan gelen talepleri kabul etmek için socket açılıyor
                Socket clientSocket = connectionSocket.accept();
                // Client dan gelen parametreleri handle ederek isteğiyle ilgili commandı çalıştırmak için
                // TaskClientHandler nesnesi oluşturuluyor
                TaskClientHandler clientHandler = new TaskClientHandler(clientSocket, taskList);
                Thread worker = new Thread(clientHandler);
                worker.start();
            }

        } catch (IOException e)
        {
            System.out.println("Problem setting up the connection socket " + e.getMessage());
        }
    }
}
