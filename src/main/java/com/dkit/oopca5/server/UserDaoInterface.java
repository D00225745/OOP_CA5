package com.dkit.oopca5.server;

public interface UserDaoInterface
{
    public List<User> findAllUsers() throws DaoException;
    public User findUserByUsernamePassword(String username, String password) throws DaoException;
}
