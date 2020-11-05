package main;

import entity.BoardingPassTrain;
import entity.Train;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

public abstract class BoardingPassWriter {
    private static String centerString(int n, String str) {
        float nn = (n - 2 - str.length()) / 2.0F;
        StringBuilder line = new StringBuilder("#");
        IntStream.range(0, (int) nn).forEach(i -> line.append(" "));
        line.append(str);
        if (nn % 1 != 0) {
            nn = nn + 1;
        }
        IntStream.range(0, (int) nn).forEach(i -> line.append(" "));
        line.append("#");
        return line.toString();
    }

    public static void write(BoardingPassTrain pass, Train train) {
        Path filepath = Paths.get(String.format("%s/src/boarding_pass_ticket_%s.txt", System.getProperty("user.dir"), pass.getId()));
        StringBuilder passStr = new StringBuilder();
        int n = 100;
        StringBuilder total = new StringBuilder().append("#".repeat(n));
        StringBuilder emptyLine = new StringBuilder("#").append(" ".repeat(n - 2)).append("#");

        String l1 = String.format("#      Name: %s   Age: %d   Gender: %s", pass.getName(), pass.getAge(), pass.getGender());
        String l2 = String.format("#      From: %s   To: %s", train.getOrigin(), train.getDestination());
        String l3 = String.format("#      Departure: %s   Arrival: %s", train.getDeparture(), pass.getEta());
        String l4 = String.format("#      Email: %s   Phone: %s", pass.getEmail(), pass.getPhone());
        String l5 = String.format("#      Ticket Price: $%.2f", pass.getTicketPrice().floatValue());

        passStr.append(String.format("%s\n", total.toString()))
               .append(String.format("%s\n", centerString(n, "****** WORLD FASTEST TRAIN ******")))
               .append(String.format("%s\n", emptyLine.toString()))
               .append(String.format("%s\n", centerString(n, "T I C K E T")))
               .append(String.format("%s\n", emptyLine.toString()))
               .append(l1).append(" ".repeat(n - l1.length() - 1)).append("#\n")
               .append(l2).append(" ".repeat(n - l2.length() - 1)).append("#\n")
               .append(l3).append(" ".repeat(n - l3.length() - 1)).append("#\n")
               .append(l4).append(" ".repeat(n - l4.length() - 1)).append("#\n")
               .append(l5).append(" ".repeat(n - l5.length() - 1)).append("#\n")
               .append(String.format("%s\n", emptyLine.toString()))
               .append(String.format("%s\n", total.toString()));
        try {
            Files.writeString(filepath, passStr.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("We could not save your boarding pass. Copy the contents below.");
            System.out.println(passStr.toString());
            System.exit(-2);
        }
    }
}
