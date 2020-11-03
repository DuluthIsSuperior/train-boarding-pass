package main;

import entity.BoardingPassTrain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.*;
import java.util.Date;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);
        System.out.println("\n" + "+----------------------------------+");
        System.out.println("+Welcome to the World Fastest Train+");
        System.out.println("+----------------------------------+\n");
        System.out.print("Please enter your Name: ");
        String name = getInput.next();
        System.out.print("Please enter your Email: ");
        String email = getInput.next();
        System.out.print("Please enter your Phone Number: ");
        String phoneNumber = getInput.next();
        System.out.print("Please enter your Gender: ");
        String gender = getInput.next();
        System.out.print("Please enter your Age: ");
        int age = getInput.nextInt();
        System.out.print("Please enter your Date(dd-MM-yyyy): ");
        String date = getInput.next();
        System.out.print("Please enter your Departure Time(hh:mm:ss): ");
        String depatureTime = getInput.next();
        System.out.print("Please enter your Destination: ");
        String destination = getInput.next();
    }

    public static float discount(float ticketPrice, int age, String gender) {
        if (age <= 12) {
            ticketPrice = ticketPrice * 0.5f;
        } else if (age >= 60) {
            ticketPrice = ticketPrice - (ticketPrice * 0.6f);
        } else if (gender.equals("Female")) {
            ticketPrice = ticketPrice - (ticketPrice * 0.25f);
        }
        return ticketPrice;
    }

    public static void saveTicket (String name, Date date, String origin, String destination, Date eta,
                                   Date departure, String email, String phone, String gender, int age,
                                   float ticketPrice) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(BoardingPassTrain.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            BoardingPassTrain myBoardingPassTrain = new BoardingPassTrain(name, date, origin, destination, eta,
                    departure, email, phone, gender, age, ticketPrice);
            session.save(myBoardingPassTrain);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
