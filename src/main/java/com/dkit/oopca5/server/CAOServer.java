package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

//Berk Tatar D00225745

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.dkit.oopca5.core.CAOService;

public class CAOServer implements Runnable {

    private static int connectedClientCount = 0;

    private Socket clientConnection;
    private InputStream receiver;
    private OutputStream sender;

    CAOServer(Socket clientConnection) throws IOException {
        this.clientConnection = clientConnection;
        this.receiver = clientConnection.getInputStream();
        this.sender = clientConnection.getOutputStream();
        System.out.printf(
                " Client connected. IP: %s, Port: %s %n"
                , clientConnection.getInetAddress().getHostAddress()
                , clientConnection.getPort()
        );
    }

    public void run() {

        DataInputStream dataReceiver = new DataInputStream(receiver);
        DataOutputStream dataSender = new DataOutputStream(sender);

        while(true) {

            try {
                // REGISTER%%12345678%%01.01.2000%%98765

                String requestString = dataReceiver.readUTF();

                String[] params = requestString.split("%%");

                String responseString = "";

                if(CAOService.REGISTER_COMMAND.equals(params[0])){
                    responseString = CAOService.SUCCESSFUL_REGISTER;
                } else if(CAOService.LOGIN_COMMAND.equals(params[0])){
                    responseString = CAOService.LOGGED_IN;
                } else if(CAOService.LOGOUT_COMMAND.equals(params[0])){
                    responseString = CAOService.LOGGED_OUT;
                } else if(CAOService.DISPLAY_COURSE_COMMAND.equals(params[0])){
                    responseString = CAOService.DISPLAY_FAILED;
                } else if(CAOService.DISPLAY_ALL_COMMAND.equals(params[0])){
                    responseString = CAOService.SUCCESSFUL_DISPLAY_ALL;
                } else if(CAOService.DISPLAY_CURRENT_COMMAND.equals(params[0])){
                    responseString = CAOService.SUCCESSFUL_DISPLAY_CURRENT;
                } else if(CAOService.UPDATE_CURRENT_COMMAND.equals(params[0])){
                    responseString = CAOService.SUCCESSFUL_UPDATE_CURRENT;
                }

                dataSender.writeUTF(responseString);

            } catch (IOException e) {
                System.out.println("Reading failed : " + e.getMessage());
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket sunucuSocket = null;

        System.out.println("Server started");
        try {
            // Port kullanimdaysa - java.net.BindException alir
            sunucuSocket = new ServerSocket(CAOService.PORT_NUM);

            System.out.println("Server Socket created. Port: " + CAOService.PORT_NUM);
        } catch (Exception e) {
            System.out.println(CAOService.PORT_NUM + " port is in use." );
        }

        boolean continueToListen = true;
        while(continueToListen) {

            System.out.println("Waiting for new client...");


            Socket istemciBaglantisi = sunucuSocket.accept();
            connectedClientCount++;

            new Thread(
                    new CAOServer(istemciBaglantisi),
                    String.valueOf(connectedClientCount)
            ).start();
        }

        System.out.println("Server is closing...");
    }
}
