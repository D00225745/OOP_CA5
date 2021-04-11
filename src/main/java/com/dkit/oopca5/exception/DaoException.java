package com.dkit.oopca5.exception;

import java.sql.SQLException;

//Berk Tatar D00225745


public class DaoException extends SQLException
{


    public DaoException(String aMessage)
    {
        super(aMessage);
    }
}