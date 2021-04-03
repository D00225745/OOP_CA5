package com.dkit.oopca5.DTO;

public class Student
{

        private int caoNumber;  // In the CAA system, cao number is unique identifier for student
        private String dateOfBirth; // yyyy-mm-dd
        private String password;    // min 8 characters


        // Copy Constructor
        // Copies the contents of a Student object argument into
        // a new Student object, and returns that new object (a clone)
        //
        public Student(Student student) {
            this.caoNumber = student.caoNumber;
            this.dateOfBirth = student.dateOfBirth;
            this.password = student.password;

        }

        public Student(int caoNumber, String dateOfBirth, String password, String email) {
            this.caoNumber = caoNumber;
            this.dateOfBirth = dateOfBirth;
            this.password = password;

        }

        //public boolean verifyLoginCredentials( yyy-mm-dd, password);

        public int getCaoNumber() {
            return caoNumber;
        }

        public void setCaoNumber(int caoNumber) {
            this.caoNumber = caoNumber;
        }

        public String getDayOfBirth() {
            return dateOfBirth;
        }

        public void setDayOfBirth(String dayOfBirth) {
            this.dateOfBirth = dayOfBirth;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }



        @Override
        public String toString() {
            return "Student{" +
                    "caoNumber=" + caoNumber +
                    ", dateOfBirth='" + dateOfBirth + '\'' +
                    ", password='" + password + '\'' + +
                    '}';
        }

}


