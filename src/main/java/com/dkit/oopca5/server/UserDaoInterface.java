package com.dkit.oopca5.server;

import java.util.List;

import com.dkit.oopca5.core.User;
import com.dkit.oopca5.exception.DaoException;

//Berk Tatar D00225745


public interface UserDaoInterface
{
    public List<User> findAllUsers() throws DaoException;
    public User findUserByUsernamePassword(String username, String password) throws DaoException;
    public boolean registerUser() throws DaoException;
}
