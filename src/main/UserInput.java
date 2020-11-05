package main;

import entity.BoardingPassTrain;
import entity.Train;
import javassist.Loader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class UserInput {
    private static final Scanner getInput = new Scanner(System.in);

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
     * Looks through the list of given strings to make sure they are in the correct date format.
     * @param dates list of strings to analyze
     * @return dateTimes if all Strings are valid dates in the format yyyy-MM-dd, else null
     */
    public static List<String> validateDates(List<String> dates) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (String dateTime : dates) {
            try {
                formatter.parse(dateTime);
            } catch (ParseException e) {
                return null;
            }
        }
        return dates;
    }

    /**
     * Looks through the list of given strings to make sure they are in the correct time format.
     * @param times list of strings to analyze
     * @return dateTimes if all Strings are valid times in the format HH:mm:ss, else null
     */
    public static List<String> validateTimes(List<String> times) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        for (String dateTime : times) {
            try {
                formatter.parse(dateTime);
            } catch (ParseException e) {
                return null;
            }
        }
        return times;
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

    public static <T> T printListGetInt(String message, List<T> list) {
        System.out.println(message);
        if (list == null) {
            System.out.println("A database error has occurred. Please try again later.");
            System.exit(1);
        }
        IntStream.range(0, list.size())
                .forEach(i -> System.out.printf("\t%d: %s\n", i + 1, list.get(i)));
        int choice = getIntRange(1, list.size());
        return list.get(choice - 1);
    }

    public static void main(String[] args) {
        BoardingPassTrain pass1 = new BoardingPassTrain();
        DepartureTable.init();  // allows hibernate to print whatever garbage it needs to to the console without hiding our print statements
        String message = "Welcome to the World Fastest Train";
        System.out.println(new StringBuilder("+").append("-".repeat(message.length())).append("+\n+")
                .append(message).append("+\n+").append("-".repeat(message.length())).append("+"));

        //*** Name User Input ***
        System.out.print("Please enter your Name: ");
        pass1.setName(getInput.nextLine());

        //*** Email User Input ***
        System.out.print("Please enter your Email: ");
        pass1.setEmail(getInput.nextLine());

        //*** Phone User Input ***
        System.out.print("Please enter your Phone Number: ");
        while (true) {
            String pN = parsePhoneNumber(getInput.nextLine());
            if (pN != null) {
                pass1.setPhone(pN);
                break;
            } else {
                getInput.next();
                System.out.println("Please try again. Your phone number must be typed in one of the following formats:");
                System.out.print("(XXX) XXX-XXXX, XXX-XXX-XXXX, or XXXXXXXXXX\n> ");
            }
        }

        //*** Gender User Input ***
        System.out.print("Please enter your Gender (Male or Female): ");
        while (true) {
            String gender = parseGender(getInput.nextLine());
            if (gender != null) {
                pass1.setGender(gender);
                break;
            } else {
                getInput.next();
                System.out.print("Sorry, I could not understand your input. Please try again: ");
            }
        }

        //*** Age User Input ***
        System.out.print("Please enter your Age: ");
        pass1.setAge(getInt());

//        pass1.setName("Kyle Dick");
//        pass1.setEmail("snooze@zzz.com");
//        pass1.setPhone("(616) 932-1023");
//        pass1.setGender("Male");
//        pass1.setAge(23);

        System.out.println("For the following prompts, select your option by typing in the number.");
        String origin = printListGetInt("Please select an origin:", DepartureTable.getOrigins());
        String destination = printListGetInt("Please select a destination:", DepartureTable.getDestinations());
        String departure = printListGetInt("Please select a departure date:",
                validateDates(DepartureTable.getDateByDestination(destination)));
        departure += " " + printListGetInt("Please select a departure time:",
                validateTimes(DepartureTable.getTimeByDateAndDestination(departure, destination))
        );

        Train t = DepartureTable.getTrain(departure, destination);
        t.setOrigin(origin);
        pass1.setTicketPrice(discount(t.getPrice(), pass1.getAge(), pass1.getGender()));
        pass1.setTrainID(t.getID());
        pass1.setEta(calculateEta(departure, t.getDistance(), 60));

        //*** Save Ticket ***
        saveTicket(pass1);

        //*** Print Ticket to Text File ***
        BoardingPassWriter.write(pass1, t);
    }

    //*** Calculates the ETA ***
    public static Date calculateEta(String departure, BigDecimal distance, int speed) {
        MyBigDecimal hour = new MyBigDecimal(distance).divide(speed, 2);
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(0);
        try {
            date = formatter.parse(departure);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("An internal error has occurred. Departure date is not saved.");
        }
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour.intValue());
        int minutes = hour.subtract(hour.intValue()).multiply(60).intValue();
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static BigDecimal discount(BigDecimal ticketPrice, int age, String gender) {
        MyBigDecimal price = new MyBigDecimal(ticketPrice);
        if (age <= 12) {
            price = price.multiply(0.5);
        } else if (age >= 60) {
            price = price.subtract(price.multiply(0.6));
        } else if (gender.equals("Female")) {
            price = price.subtract(price.multiply(0.25));
        }
        return price.round(2);
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
