package com.dkit.oopca5.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dkit.oopca5.core.CAOService;

//Berk Tatar D00225745


public class CAOClient {

    // kullanıcının menü seçimi ve veri girişi yapabilmesi için
    private static Scanner keyboard = new Scanner(System.in);

    private static String sendRequest(String params) throws UnknownHostException, IOException {

        Socket connection = new Socket(CAOService.HOSTNAME, CAOService.PORT_NUM);

        System.out.println("Connected to server. IP: "
                + connection.getInetAddress().getHostName());

        InputStream receiver = connection.getInputStream();
        OutputStream sender = connection.getOutputStream();

        DataInputStream dataReceiver = new DataInputStream(receiver);
        DataOutputStream dataSender = new DataOutputStream(sender);

        // resimdeki iki yönlü veri trafiği
        dataSender.writeUTF(params); // servera veri transfer eder

        String response = dataReceiver.readUTF(); // server veri döner

        return response;
    }

    public static void main(String[] args) {
        System.out.println("CAO Online - CA5");
        new CAOClient().start();
    }

    private void start() {
        doMainMenuLoop();
    }

    private void doMainMenuLoop() {

        boolean loop = true;
        int option = -1;
        MainMenu mainMenuOption;
        boolean loggedIn = false;
        int caoNumber = -1;
        String password = "";

        while (loop) {
            printMainMenu();

            try {
                option = keyboard.nextInt();

                if (option < 0 || option >= MainMenu.values().length) {
                    throw new IllegalArgumentException();
                }

                mainMenuOption = MainMenu.values()[option];

                switch (mainMenuOption) {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case REGISTER:
                        System.out.println("CAO Number:");
                        caoNumber = Integer.parseInt(keyboard.next());
                        System.out.println("Date of Birth:");
                        String dateOfBirth = keyboard.next();
                        System.out.println("password:");
                        password = keyboard.next();

                        String requestString = MainMenu.REGISTER.toString()
                                + CAOService.BREAKING_CHARACTER + caoNumber
                                +  CAOService.BREAKING_CHARACTER + dateOfBirth
                                +  CAOService.BREAKING_CHARACTER + password;

                        String result = sendRequest(requestString);

                        if("REGISTERED".equals(result)){
                            System.out.println("User is " + result + " sucessfully.");
                            continue;
                        }

                        break;
                    case LOG_IN:
                        if (!loggedIn) {
                            System.out.println("CAO Number:");
                            caoNumber = Integer.parseInt(keyboard.next());
                            System.out.println("password:");
                            password = keyboard.next();

                            if (caoNumber != 1) {
                                loggedIn = true;
                            }
                        } else {
                            System.out
                                    .println("This user is not registered.. please register first...");
                            continue; // sisteme login olamadığı için ana menüye
                            // geri gönderiyoruz
                        }
                }

                // login olmayan kullanıcı buradan sonra ilerleyemez tekrar
                // while döngüsünün en başına gider ve
                // ana menü gösterilir
                if (!loggedIn)
                    continue;

                doCourseMenuLoop();

            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid option");
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter a valid option");
            } catch (UnknownHostException e) {
                System.out.println("Cannot connect to server");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Thanks for using the app");
    }

    private void doCourseMenuLoop() {
        boolean loop = true;

        while (loop) {
            try {
                printCourseMenu();

                int option = keyboard.nextInt();
                CourseMenu courseMenuOption = CourseMenu.values()[option];

                switch (courseMenuOption) {
                    case QUIT:
                        loop = false;
                        break;
                    case LOGOUT:
                        // TODO
                        break;
                    case DISPLAY_COURSE:
                        // TODO
                        break;
                    case DISPLAY_ALL_COURSES:
                        // TODO
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        // TODO
                        break;
                    case UPDATE_CURRENT_CHOICES:
                        // TODO
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid option");
            }
        }
    }

    private void printCourseMenu() {
        System.out.println("\n Options to select:");
        for (int i = 0; i < CourseMenu.values().length; i++) {
            System.out.println("\t" + i + ". "
                    + CourseMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printMainMenu() {
        System.out.println("\n Options to select:");
        for (int i = 0; i < MainMenu.values().length; i++) {
            System.out.println("\t" + i + ". "
                    + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

}
