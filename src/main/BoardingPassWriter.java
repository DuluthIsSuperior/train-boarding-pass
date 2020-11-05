package main;

import entity.BoardingPassTrain;
import entity.Train;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

public abstract class BoardingPassWriter {
    public static void write(BoardingPassTrain myBoardingPassTrain, Train myTrain) {
        Path filepath = Paths.get(String.format("%s/src/boarding_pass_ticket_%s.txt", System.getProperty("user.dir"), myBoardingPassTrain.getId()));
        StringBuilder total = new StringBuilder();
        int n = 100;
        IntStream.range(0, n + 1).forEach(i -> total.append("#"));
        StringBuilder emptyLine = new StringBuilder("#");
        IntStream.range(0, n - 1).forEach(i -> emptyLine.append(" "));
        emptyLine.append("#");

        String name =  "#      Name: ";
        String age = "   Age: ";
        String gender = "   Gender: ";
        String from = "#      From: ";
        String to = "   To: ";
        String departure = "#      Departure: ";
        String arrival = "   Arrival: ";
        String email= "#      Email: ";
        String cellphone = "   Cellphone: ";
        String ticketPrice = "#      Ticket Price: $";

        int spaceLeft1 = n - name.length() - myBoardingPassTrain.getName().length() - age.length() - String.valueOf(myBoardingPassTrain.getAge()).length() - gender.length() - myBoardingPassTrain.getGender().length();
        int spaceLeft2 = n - from.length() - myTrain.getOrigin().length() - to.length() - myTrain.getDestination().length();
        int spaceLeft3 = n - departure.length() - myTrain.getDeparture().length() - arrival.length() - myBoardingPassTrain.getEta().toString().length();
        int spaceLeft4 = n -  email.length() - myBoardingPassTrain.getEmail().length() - cellphone.length() - myBoardingPassTrain.getPhone().length();
        int spaceLeft5 = n - ticketPrice.length() - String.valueOf(myBoardingPassTrain.getTicketPrice()).length();
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        for (int i = 0; i < spaceLeft1; i++) {
            line1 += " ";
        }
        for (int i = 0; i < spaceLeft2; i++) {
            line2 += " ";
        }
        for (int i = 0; i < spaceLeft3; i++) {
            line3 += " ";
        }
        for (int i = 0; i < spaceLeft4; i++) {
            line4 += " ";
        }
        for (int i = 0; i < spaceLeft5; i++) {
            line5 += " ";
        }
        try {
            Files.writeString(filepath, String.format("%s\n", total.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("#                                ****** WORLD FASTEST TRAIN ******                                  #\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", emptyLine.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("#                                           T I C K E T                                             #\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", emptyLine.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, (name + myBoardingPassTrain.getName() + age + myBoardingPassTrain.getAge() + gender + myBoardingPassTrain.getGender() + line1 + "#\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, (from + myTrain.getOrigin() + to + myTrain.getDestination() + line2 + "#\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, (departure + myTrain.getDeparture() + arrival + myBoardingPassTrain.getEta() + line3 + "#\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, (email + myBoardingPassTrain.getEmail() + cellphone + myBoardingPassTrain.getPhone() + line4 + "#\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, (ticketPrice + myBoardingPassTrain.getTicketPrice() + line5 + "#\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(filepath, ("#                                                                                                   #\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", total.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e){
            System.out.println("File does not exist");
            System.exit(-2);
        }
    }
}
