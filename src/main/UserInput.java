package main;

import entity.BoardingPassTrain;
import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    static Path filepath = Paths.get(System.getProperty("user.dir") + "/src/boarding_pass_ticket.txt");

    private static void write(Path filepath, BoardingPassTrain myBoardingPassTrain) {
        try {
            Files.write(filepath, ("Your name: " + myBoardingPassTrain.getName() + "   Age: " + myBoardingPassTrain.getAge() + "   Gender: " + myBoardingPassTrain.getGender() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("From: ?   To: ?\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("Departure: ?   Arrival: " + myBoardingPassTrain.getEta() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("Email: " + myBoardingPassTrain.getEmail() + "   Cellphone: " + myBoardingPassTrain.getPhone() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("Ticket Price: $" + myBoardingPassTrain.getTicketPrice()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (Exception e){
            System.out.println("File does not exist");
            System.exit(-2);
        }
    }

    /**
     * Gets an integer from the user within the specified range. Keeps asking until a valid integer is received.
     * @param from starting integer of the range
     * @param to inclusive ending integer of the range
     * @return the integer from the user within the given range
     */
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

    /**
     * Gets an integer from the user input. Keeps asking until a valid integer is received.
     * @return an integer from the user input
     */
    private static int getInt() {
        return getIntRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Checks the format of the given string to see if it's a valid phone number
     * @param phoneNumber string to parse
     * @return phone number in (XXX) XXX-XXXX format if valid, otherwise null
     */
    public static String parsePhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}")) {
            return phoneNumber;
        } else if (phoneNumber.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
            String[] numbers = phoneNumber.split("-");
            return String.format("(%s) %s-%s", numbers[0], numbers[1], numbers[2]);
        } else if (phoneNumber.matches("[0-9]{10}")) {
            return String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6, 10));
        }
        return null;
    }

    /**
     * Checks to see if the given string is a valid gender
     * @param gender string to parse
     * @return Male or Female if the gender is valid; otherwise null
     */
    public static String parseGender(String gender) {
        if (gender.matches("(M|m)ale|(F|f)emale")) {
            return String.format("%s%s", gender.substring(0, 1).toUpperCase(), gender.substring(1));
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {


        BoardingPassTrain pass1 = new BoardingPassTrain();
        String message = "Welcome to the World Fastest Train";
        System.out.print("+");
        IntStream.range(0, message.length()).forEach(i -> System.out.print("-"));
        System.out.printf("\n+%s+\n+", message);
        IntStream.range(0, message.length()).forEach(i -> System.out.printf("-%s", i != message.length() - 1 ? "" : "+\n"));


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
        while (true) {
            String pN = parsePhoneNumber(getInput.nextLine());
            if (pN != null) {
                pass1.setPhone(pN);
                break;
            } else {
                System.out.print("Sorry, I did not understand your input. Please try again: ");
            }
        }
//        pass1.setPhone("(616) 299-9438");

        //*** Gender User Input ***
        System.out.print("Please enter your Gender (Male or Female): ");
        while (true) {
            String gender = parseGender(getInput.nextLine());
            if (gender != null) {
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

        List<String> origins = DepartureTable.getOrigins();
        System.out.println("Please select an origin:");
        IntStream.range(0, origins.size())
                .forEach(i -> System.out.printf("\t%d: %s\n", i + 1, origins.get(i)));
        int choice = getIntRange(1, origins.size());
        String origin = origins.get(choice - 1);

        List<String> destinations = DepartureTable.getDestinations();
        System.out.println("Please select a destination:");
        IntStream.range(0, destinations.size())
                .forEach(i -> System.out.printf("\t%d: %s\n", i + 1, destinations.get(i)));
        choice = getIntRange(1, destinations.size());
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

        //Get distance from schedule table
        BigDecimal distance = DepartureTable.getDistance(pass1.getDestination());
        float ticketPrice = DepartureTable.getTicketPrice(pass1.getDestination());

        pass1.setDeparture(departure.getTime());

        //Apply discount and set ticketprice to BoardingPassTrain object
        pass1.setTicketPrice(discount(ticketPrice, pass1.getAge(), pass1.getGender()));

        //Calculate ETA and set ETA to BoardingPassTrain object
        pass1.setEta(calculateEta(pass1.getDeparture(), distance, new BigDecimal(352)));

        //Send User inputs to database
        saveTicket(pass1);

        write(filepath, pass1);
    }

    //Calculate the ETA
    public static Date calculateEta(Date departure, BigDecimal distance, BigDecimal speed){

        BigDecimal hour = distance.setScale(2, RoundingMode.UNNECESSARY).divide(speed, RoundingMode.HALF_UP);
        BigDecimal minute = hour.subtract(new BigDecimal(hour.intValue())).multiply(new BigDecimal(60));
        BigDecimal second = minute.subtract(new BigDecimal(minute.intValue())).multiply(new BigDecimal(60));
        Calendar cal = new GregorianCalendar();
        cal.setTime(departure);
        cal.add(Calendar.HOUR_OF_DAY, hour.intValue());
        cal.add(Calendar.MINUTE, minute.intValue());
        cal.add(Calendar.SECOND, second.intValue());
        return cal.getTime();

    }

    //Calculate discount on ticketPrice
    public static float discount(float ticketPrice, int age, String gender) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (age <= 12) {
            ticketPrice = Float.valueOf(decimalFormat.format(ticketPrice * 0.5f));
        } else if (age >= 60) {
            ticketPrice = Float.valueOf(decimalFormat.format(ticketPrice - (ticketPrice * 0.6f)));
        } else if (gender.equals("Female")) {
            ticketPrice = Float.valueOf(decimalFormat.format(ticketPrice - (ticketPrice * 0.25f)));
        }
        return ticketPrice.setScale(2, RoundingMode.HALF_UP);
    }

    //Send user all information to database
    public static void saveTicket(BoardingPassTrain myBoardingPassTrain) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(BoardingPassTrain.class)
            .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(myBoardingPassTrain);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
