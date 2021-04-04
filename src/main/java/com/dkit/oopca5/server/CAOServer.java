package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

//Berk Tatar D00225745 and Emmanuel Francis D00228281


import com.dkit.oopca5.exception.DaoException;

public class CAOServer
{
    public static void main(String[] args)
    {
        System.out.println("Database access");
        UserDaoInterface UserDao = new MySqlUserDao();

        getAllUsers(UserDao);
        //getSpecificUser(UserDao, "Berk", "catNdogs");
    }

    private static void getAllUsers(UserDaoInterface UserDao)
    {
        try
        {
            List<User> users = UserDao.findAllUsers();
            printUsers(users);
        }
        catch(DaoException daoe)
        {
            System.out.println(daoe.getMessage());
        }

    }

    private static void printUsers(List<User> users)
    {
        if(users.isEmpty())
        {
            System.out.println("There are no users");
        }

        for(User user : users)
        {
            System.out.println(user);
        }
    }


}

