import entity.BoardingPassTrain;

import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class UserInput {
    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);
        BoardingPassTrain pass1 = new BoardingPassTrain();
//        System.out.println("\n" + "+----------------------------------+");
//        System.out.println("+Welcome to the World Fastest Train+");
//        System.out.println("+----------------------------------+\n");
//        System.out.print("Please enter your Name: ");
//        String name = getInput.next();
//        System.out.print("Please enter your Email: ");
//        String email = getInput.next();
//        System.out.print("Please enter your Phone Number: ");
//        String phoneNumber = getInput.next();
//        System.out.print("Please enter your Gender: ");
//        String gender = getInput.next();
//        System.out.print("Please enter your Age: ");
//        int age = getInput.nextInt();

        //Date User Input
        System.out.print("Please enter your Date(dd-MM-yyyy hh:mm:ss): ");
        String date = getInput.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date2=null;
        try {
            //Parsing the String
            date2 = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(date2);

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
       // String depatureTime = getInput.next();
//        String destination = getInput.next();

    }

    public static void getDepatureTimeandDate () {

    }
}
