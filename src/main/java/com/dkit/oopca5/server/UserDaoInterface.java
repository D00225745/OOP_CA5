package com.dkit.oopca5.server;

import java.util.List;

import com.dkit.oopca5.core.User;
import com.dkit.oopca5.exception.DaoException;

public interface UserDaoInterface
{
    public List<User> findAllUsers() throws DaoException;
    public User findUserByUsernamePassword(String username, String password) throws DaoException;
    public boolean registerUser() throws DaoException;
}
