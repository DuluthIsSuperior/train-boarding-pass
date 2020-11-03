import entity.BoardingPassTrain;

import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class UserInput {
    public static void main(String[] args) throws ParseException {

        Scanner getInput = new Scanner(System.in);
        BoardingPassTrain pass1 = new BoardingPassTrain();
        System.out.println("\n" + "+----------------------------------+");
        System.out.println("+Welcome to the World Fastest Train+");
        System.out.println("+----------------------------------+\n");

        //*** Name User Input ***
        System.out.print("Please enter your Name: ");
        String name = getInput.next();
        pass1.setName(name);
        System.out.println(pass1.getName());

        //*** Email User Input ***
        System.out.print("Please enter your Email: ");
        String email = getInput.next();
        pass1.setEmail(email);
        System.out.println(pass1.getEmail());

        //*** Phone User Input ***
        System.out.print("Please enter your Phone Number: ");
        String phoneNumber = getInput.next();
        pass1.setPhone(phoneNumber);
        System.out.println(pass1.getPhone());

        //*** Gender User Input ***
        System.out.print("Please enter your Gender: ");
        String gender = getInput.next();
        pass1.setGender(gender);
        System.out.println(pass1.getGender());

        //*** Age User Input ***
        System.out.print("Please enter your Age: ");
        int age = getInput.nextInt();
        pass1.setAge(age);
        System.out.println(pass1.getAge());

        //Departure User Input
        System.out.print("Please enter your Destination: ");
        String destination = getInput.next();
        pass1.setDestination(destination);
        System.out.println(pass1.getDestination());
        //var x = LocalDateTime.of(1998, Month.DECEMBER, 28, 12, 53);
        //System.out.println(x);
        //Date User Input
        System.out.print("Please enter your Date(dd-MM-yyyy HH:mm:ss): ");
        String date = getInput.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //dateFormat.toLocalizedPattern();
        System.out.println(dateFormat);
        Date date1 = dateFormat.parse(date);
        System.out.println(date1);
//        Date date2=null;
//        try {
//            //Parsing the String
//            date2 = dateFormat.parse(date);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println(date2);

//        Time User Input
//        System.out.print("Please enter your Departure time: ");
//        String depatureTime = getInput.next();
//        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
//        Date time1=null;
//        try {
//            //Parsing the String
//            time1 = timeFormat.parse(depatureTime);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println(time1);
        //Departure User Input
       // String depatureTime = getInput.next();

    }

    public static void getDepatureTimeandDate () {

    }
}
