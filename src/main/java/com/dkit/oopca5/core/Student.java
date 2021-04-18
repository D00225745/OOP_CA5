package com.dkit.oopca5.core;

import java.util.Objects;

//Berk Tatar D00225745


public class Student {
    private int caoNumber;
    private String dateOfBirth;
    private String password;

    public Student(int caoNumber, String dateOfBirth, String password){

        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public int getCaoNumber() {
        return caoNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }
}