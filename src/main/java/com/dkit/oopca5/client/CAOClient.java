
import java.io.IOException;
import java.util.regex.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.omg.CORBA.RepositoryIdHelper;

import com.dkit.oopca5.core.CAOService;

public class Client {
    //login olduktan sonra caonumberı global kullanmak için
    static int CAONumber ;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int option = -1;
        MainMenu mainMenuOption;

        int caoNumber = -1;
        String password = "";
        String dateOfBirth = "";
        String response = "";
        String toBeSent = "";
        Socket dataSocket = null;
        Scanner serverIn = null;

        try {
            dataSocket = new Socket(CAOService.HOSTNAME, CAOService.PORT_NUM);
            serverIn = new Scanner(dataSocket.getInputStream());
            PrintWriter serverOut = new PrintWriter(
                    dataSocket.getOutputStream(), true);

            boolean keepRunning = true;
            while (keepRunning) {
                boolean loggedIn = false;
                printMainMenu();

                option = keyboard.nextInt();

                if (option < 0 || option >= MainMenu.values().length) {
                    throw new IllegalArgumentException();
                }

                mainMenuOption = MainMenu.values()[option];

                switch (mainMenuOption) {
                    case QUIT_APPLICATION:
                        keepRunning = false;
                        break;
                    case REGISTER:
                        // Kullanıcının veri girişi
                        System.out.println("CAO Number:");
                        String strCAONumber = keyboard.next();
                        caoNumber = Integer.parseInt(strCAONumber);
                        System.out.println("Date of Birth:");
                        dateOfBirth = keyboard.next();
                        System.out.println("password:");
                        password = keyboard.next();

                        // {8} basamaklı olduğunu [0-9] her basamakta bi rakam
                        // olabileceği
                        if (Pattern.matches("[0-9]{8}", strCAONumber) == false) {
                            System.out.println("Incorrect CAONumber");
                            continue;
                            // [0-9]{4} tairihin yıl bölümü - \\d{2} 1 veya 2
                            // basamaklı sayı
                        } else if (Pattern
                                .matches(
                                        "([12]\\d{3}-([1-9]|1[0-2])-([1-9]|[12]\\d|3[01]))",
                                        dateOfBirth) == false) {
                            System.out.println("Incorrect date");
                            continue;
                            // password must be at least 8 characters long
                        } else if (password.length() < 8) {
                            System.out
                                    .println("Password must be 8 characters at least.");
                            continue;
                        }

                        // Client request oluşturuyoruz
                        toBeSent = CAOService.REGISTER_COMMAND.toString()
                                + CAOService.BREAKING_CHARACTER + caoNumber
                                + CAOService.BREAKING_CHARACTER + dateOfBirth
                                + CAOService.BREAKING_CHARACTER + password;

                        // Kullanıcının seçimleriin ve girdiği değerleri sunucu
                        // tarafına gönderiyoruz
                        serverOut.println(toBeSent);

                        // Sunucu çalıştıktan sonra bize döndüğü mesajı alıyoruz
                        response = serverIn.nextLine();

                        if ("REGISTERED".equals(response)) {
                            System.out.println("User is " + response
                                    + " sucessfully.");

                            continue; // Kullanıcı register olduktan sonra login
                            // veya qit olabilmesi için ana menuyu
                            // tekrar göstermek
                            // amacıyla döngünün başına gidiyor
                        } else {
                            System.out.println(response);
                        }

                        break;
                    case LOG_IN:
                        if (!loggedIn) {
                            // Kullanıcının veri girişi
                            System.out.println("CAO Number:");
                            caoNumber = Integer.parseInt(keyboard.next());
                            System.out.println("Date of Birth:");
                            dateOfBirth = keyboard.next();
                            System.out.println("password:");
                            password = keyboard.next();

                            // Client request oluşturuyoruz
                            toBeSent = CAOService.LOGIN_COMMAND.toString()
                                    + CAOService.BREAKING_CHARACTER + caoNumber
                                    + CAOService.BREAKING_CHARACTER + dateOfBirth
                                    + CAOService.BREAKING_CHARACTER + password;

                            // Kullanıcının seçimleriin ve girdiği değerleri sunucu
                            // tarafına gönderiyoruz
                            serverOut.println(toBeSent);

                            // Sunucu çalıştıktan sonra bize döndüğü mesajı alıyoruz
                            response = serverIn.nextLine();

                            if (CAOService.LOGGED_IN.equals(response)) {
                                loggedIn = true;
                                CAONumber = caoNumber;
                                System.out.println("User is " + response
                                        + " sucessfully.");

                            } else {
                                System.out.println(response);
                            }

                        } else {
                            System.out
                                    .println("This user is not registered.. please register first...");

                        }
                }

                System.out
                        .println("Do you wish to contine? (-1 to end, any other key to continue");

                // p/n
                String choice = keyboard.nextLine();
                if (choice.equals("-1")) {
                    keepRunning = false;
                }

                if (loggedIn) {
                    doCourseMenuLoop(serverIn, serverOut);
                }

            }
            System.out.println("Thank you for using the TaskManager Client");

        } catch (UnknownHostException e) {
            System.out.println("Could not connect to Server " + e.getMessage());
        } catch (IOException e) {
            System.out
                    .println("Problem setting up the streams or connecting to server. Check the server is running... "
                            + e.getMessage());
        } finally {
            try {
                dataSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            serverIn.close();
        }
    }

    private static void doCourseMenuLoop(Scanner serverIn, PrintWriter serverOut) {
        boolean loop = true;
        Scanner keyboard = new Scanner(System.in);
        String response = "";

        String[] currentChoices  = null;

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
                        serverOut.println(CAOService.LOGGED_OUT);
                        response = serverIn.nextLine();
                        System.out.println(response);
                        loop = false;
                        break;
                    case DISPLAY_COURSE:
                        System.out.println("course id:");
                        String courseid = keyboard.next();
                        serverOut.println(CAOService.DISPLAY_COURSE_COMMAND + CAOService.BREAKING_CHARACTER + courseid);

                        response = serverIn.nextLine();

                        if(CAOService.DISPLAY_FAILED.equals(response)){
                            System.out.println(response);
                        } else{
                            String[] courseInfo = response.split(CAOService.BREAKING_CHARACTER);
                            System.out.println("course id:" + courseInfo[0]);
                            System.out.println("course level:" + courseInfo[1]);
                            System.out.println("course title:" + courseInfo[2]);
                            System.out.println("institution:" + courseInfo[3]);
                        }

                        break;
                    case DISPLAY_ALL_COURSES:
                        serverOut.println(CAOService.DISPLAY_ALL_COMMAND);
                        response = serverIn.nextLine();

                        System.out.println(response);

                        break;
                    case DISPLAY_CURRENT_CHOICES:

                        serverOut.println(CAOService.DISPLAY_CURRENT_COMMAND + CAOService.BREAKING_CHARACTER + CAONumber);

                        response = serverIn.nextLine();

                        String[] choices = response.split("%%");
                        currentChoices = new String[choices.length-1];

                        for (int i = 1; i < choices.length; i++) {
                            currentChoices[i-1] = choices[i];
                        }

                        System.out.println(response);
                        break;
                    case UPDATE_CURRENT_CHOICES:
                        String request = CAOService.UPDATE_CURRENT_COMMAND+ CAOService.BREAKING_CHARACTER + CAONumber;

                        for (int i = 0; i < currentChoices.length; i++) {
                            request += CAOService.BREAKING_CHARACTER + currentChoices[i];
                        }

                        serverOut.println(request);
                        response = serverIn.nextLine();



                        System.out.println(response);
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid option");
            }
        }
    }

    private static void printCourseMenu() {
        System.out.println("\n Options to select:");
        for (int i = 0; i < CourseMenu.values().length; i++) {
            System.out.println("\t" + i + ". "
                    + CourseMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private static void printMainMenu() {
        System.out.println("\n Options to select:");
        for (int i = 0; i < MainMenu.values().length; i++) {
            System.out.println("\t" + i + ". "
                    + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }
}
