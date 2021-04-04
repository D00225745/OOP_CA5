package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

//Berk Tatar D00225745 and Emmanuel Francis D00228281


public class CAOServer
{
    public static void main(String[] args)
    {
        System.out.println("Database access");
        UserDaoInterface UserDao = new MySqlUserDao();

        getAllUsers
    }


}

