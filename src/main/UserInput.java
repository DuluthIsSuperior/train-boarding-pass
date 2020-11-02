package main;

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

    public static void getDepatureTimeandDate () {

    }
}
