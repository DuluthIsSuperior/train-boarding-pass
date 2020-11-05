package main;

import entity.BoardingPassTrain;
import entity.Train;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

public abstract class BoardingPassWriter {
    private static String centerString(int n, String str) {
        float nn = (n - 2 - str.length()) / 2.0F;
        System.out.println(n + " " + (int) nn + " " + str.length());
        StringBuilder line = new StringBuilder("#");
        IntStream.range(0, (int) nn).forEach(i -> line.append(" "));
        line.append(str);
        if (nn % 1 != 0) {
            nn = nn + 1;
        }
        System.out.println((int) nn);
        IntStream.range(0, (int) nn).forEach(i -> line.append(" "));
        line.append("#");
        return line.toString();
    }

    public static void write(BoardingPassTrain pass, Train train) {
        Path filepath = Paths.get(String.format("%s/src/boarding_pass_ticket_%s.txt", System.getProperty("user.dir"), pass.getId()));
        StringBuilder total = new StringBuilder();
        int n = 100;
        IntStream.range(0, n).forEach(i -> total.append("#"));
        StringBuilder emptyLine = new StringBuilder("#");
        IntStream.range(0, n - 2).forEach(i -> emptyLine.append(" "));
        emptyLine.append("#");

        String name = String.format("#      Name: %s", pass.getName());
        String age = String.format("   Age: %d", pass.getAge());
        String gender = String.format("   Gender: %s", pass.getGender());
        String from = String.format("#      From: %s", train.getOrigin());
        String to = String.format("   To: %s", train.getDestination());
        String departure = String.format("#      Departure: %s", train.getDeparture());
        String arrival = String.format("   Arrival: %s", pass.getEta());
        String email= String.format("#      Email: %s", pass.getEmail());
        String phone = String.format("   Phone: %s", pass.getPhone());
        String ticketPrice = String.format("#      Ticket Price: $%.2f", pass.getTicketPrice().floatValue());

        int spaceLeft1 = n - name.length() - age.length() - gender.length() - 1;
        int spaceLeft2 = n - from.length() - to.length() - 1;
        int spaceLeft3 = n - departure.length() - arrival.length() - 1;
        int spaceLeft4 = n -  email.length() - phone.length() - 1;
        int spaceLeft5 = n - ticketPrice.length() - 1;
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
            Files.writeString(filepath, String.format("%s\n", centerString(n, "****** WORLD FASTEST TRAIN ******")), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", emptyLine.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", centerString(n, "T I C K E T")), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", emptyLine.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, name + age + gender + line1 + "#\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, from + to + line2 + "#\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, departure + arrival + line3 + "#\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, email + phone + line4 + "#\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, ticketPrice + line5 + "#\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", emptyLine.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(filepath, String.format("%s\n", total.toString()), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e){
            System.out.println("File does not exist");
            System.exit(-2);
        }
    }
}
