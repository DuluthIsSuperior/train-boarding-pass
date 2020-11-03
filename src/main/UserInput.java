package main;

import entity.BoardingPassTrain;
import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        BoardingPassTrain pass1 = new BoardingPassTrain();
        String message = "Welcome to the World Fastest Train";
        System.out.print("+");
        IntStream.range(0, message.length()).forEach(i -> System.out.print("-"));
        System.out.printf("\n+%s+\n+", message);
        IntStream.range(0, message.length()).forEach(i -> System.out.printf("-%s", i != message.length() - 1 ? "" : "+\n"));
        pass1.setOrigin("Conklin");

        //*** Name User Input ***
        System.out.print("Please enter your Name: ");
        pass1.setName(getInput.nextLine());
//        pass1.setName("Kyle Dick");

        //*** Email User Input ***
        System.out.print("Please enter your Email: ");
        pass1.setEmail(getInput.nextLine());
//        pass1.setEmail("snooze@zzz.com");

        //*** Phone User Input ***
        System.out.print("Please enter your Phone Number (XXX) XXX-XXXX: ");
        String pN;
        while (true) {
            String phoneNumber = getInput.nextLine();
            if (phoneNumber.matches("\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}")) {
                pN = phoneNumber;
                break;
            } else if (phoneNumber.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
                String[] numbers = phoneNumber.split("-");
                pN = String.format("(%s) %s-%s", numbers[0], numbers[1], numbers[2]);
                break;
            } else if (phoneNumber.matches("[0-9]{10}")) {
                pN = String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6, 10));
                break;
            } else {
                System.out.print("Sorry, I could not understand your input. Please try again: ");
            }
        }
        pass1.setPhone(pN);
//        pass1.setPhone("(616) 299-9438");

        //*** Gender User Input ***
        System.out.print("Please enter your Gender (Male or Female): ");
        while (true) {
            String gender = getInput.nextLine();
            if (gender.matches("Male|Female")) {
                pass1.setGender(gender);
                break;
            } else {
                System.out.print("Sorry, I could not understand your input. Please try again: ");
            }
        }
//        pass1.setGender("Male");

        //*** Age User Input ***
        System.out.print("Please enter your Age: ");
        pass1.setAge(getInt());
//        pass1.setAge(23);

        List<String> destinations = DepartureTable.getDestinations();
        System.out.println("Please select a destination:");
        IntStream.range(0, destinations.size())
                .forEach(i -> System.out.printf("\t%d: %s\n", i + 1, destinations.get(i)));
        int choice = getIntRange(1, destinations.size());
        String destination = destinations.get(choice - 1);

        List<Calendar> departureDates = DepartureTable.getDateByDestination(destination);
        System.out.println("Please select a departure date:");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < departureDates.size(); i++) {
            System.out.printf("\t%d: %s\n", i + 1, formatter.format(departureDates.get(i).getTime()));
        }
        choice = getIntRange(1, departureDates.size());
        Calendar departure = departureDates.get(choice - 1);

        List<Calendar> departureTimes = DepartureTable.getTimeByDateAndDestination(departure, destination);
        System.out.println("Please select a departure time:");
        formatter = new SimpleDateFormat("HH:mm");
        for (int i = 0; i < departureTimes.size(); i++) {
            System.out.printf("\t%d: %s\n", i + 1, formatter.format(departureTimes.get(i).getTime()));
        }
        choice = getIntRange(1, departureTimes.size());
        departure = departureTimes.get(choice - 1);

        Train t = DepartureTable.getTrain(departure, destination);
        pass1.setTicketPrice(discount(t.getPrice().floatValue(), pass1.getAge(), pass1.getGender()));
        pass1.setTrainID(t.getID());
        saveTicket(pass1);
    }

    public static Date calculateEta(Date departure, int distance, int speed){
        int time = distance/speed;
        Calendar cal = new GregorianCalendar();
        cal.setTime(departure);
        cal.add(Calendar.HOUR_OF_DAY,time);
        return cal.getTime();
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
