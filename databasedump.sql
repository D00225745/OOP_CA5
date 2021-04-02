create database OOP_CA5_Berk_Tatar_Emmanuel_Francis;
use OOP_CA5_Berk_Tatar_Emmanuel_Francis;
create table course(courseid varchar(10) PRIMARY KEY, level INT, title VARCHAR(50), institutuion VARCHAR(30) );
create table student(caoNumber int PRIMARY KEY, date_of_birth VARCHAR(10),  password VARCHAR(30) );
create table student_courses(caoNumber int PRIMARY KEY, courseid VARCHAR(10) );