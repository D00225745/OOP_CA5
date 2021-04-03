package com.dkit.oopca5.DAO;

import com.dkit.oopca5.Exceptions.DaoException;

public interface UserDaoInterface
{
    public List<User> findAllUsers() throws DaoException;

    public User findUsersByUsernamePassword(String uname, String pword) throws DaoException;
}
