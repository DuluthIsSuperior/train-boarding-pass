package main;

import entity.BoardingPassTrain;
import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.*;
import java.util.*;
import java.util.stream.IntStream;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class UserInput {
    private static final Scanner getInput = new Scanner(System.in);

    private static int getIntRange(int from, int to) {
        while (true) {
            try {
                int input = getInput.nextInt();
                if (input < from || input > to) {
                    System.out.print("Sorry, your input is outside the allowed range. Please try again: ");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.print("Sorry, I did not understand your input. Please try again: ");
            }
        }
    }

    private static int getInt() {
        return getIntRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws ParseException {
        String message = "Welcome to the World Fastest Train";
//        System.out.print("+");
//        IntStream.range(0, message.length()).forEach(i -> System.out.print("-"));
//        System.out.printf("\n+%s+\n+", message);
//        IntStream.range(0, message.length()).forEach(i -> System.out.printf("-%s", i != message.length() - 1 ? "" : "+\n"));
        Scanner getInput = new Scanner(System.in);

        //*** Name User Input ***
        System.out.print("Please enter your Name: ");
        String name = getInput.nextLine();


        //*** Origin User Input ***
        System.out.print("Please enter your Origin: ");
        String origin = getInput.nextLine();

        //*** Gender User Input ***
        System.out.print("Please enter your Gender (Male or Female): ");
        while (true) {
            String gender = getInput.nextLine();
            if (gender.matches("(M|m)ale|(F|f)emale")) {
                pass1.setGender(gender);
                break;
            } else {
                System.out.print("Sorry, I could not understand your input. Please try again: ");
            }
        }
//        pass1.setGender("Male");

        //Departure User Input
        System.out.print("Please enter your Departure Date(dd-MM-yyyy HH:mm:ss): ");
        String date = getInput.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date1 = dateFormat.parse(date);
        //System.out.println(date1);

        //Set and Calculate ETA
        Date eta = calculateEta(date1,1000, 120);
        //System.out.println(eta);


//        //*** Email User Input ***
        System.out.print("Please enter your Email: ");
        String email = getInput.nextLine();

        Train t = DepartureTable.getTrain(departure, destination);
        pass1.setTicketPrice(discount(t.getPrice().floatValue(), pass1.getAge(), pass1.getGender()));
        pass1.setTrainID(t.getID());
        calculateEta(departure.getTime(), t.getDistance(), new BigDecimal(60));
        saveTicket(pass1);
    }

    public static Date calculateEta(Date departure, BigDecimal distance, BigDecimal speed){
        BigDecimal hour = distance.divide(speed, RoundingMode.HALF_UP);
        Calendar cal = new GregorianCalendar();
        cal.setTime(departure);
        cal.add(Calendar.HOUR_OF_DAY, hour.intValue());
        BigDecimal minutes = hour.subtract(new BigDecimal(hour.intValue())).multiply(new BigDecimal(60));
        return cal.getTime();

    }

    public static float discount(float ticketPrice, int age, String gender) {
        if (age <= 12) {
            ticketPrice = ticketPrice * 0.5f;
        } else if (age >= 60) {
            ticketPrice = ticketPrice - (ticketPrice * 0.6f);
        }
        if (gender.equals("Female")) {
            ticketPrice = ticketPrice - (ticketPrice * 0.25f);
        }
        return ticketPrice;
    }

    public static void saveTicket(BoardingPassTrain pass) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(BoardingPassTrain.class)
            .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(pass);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
