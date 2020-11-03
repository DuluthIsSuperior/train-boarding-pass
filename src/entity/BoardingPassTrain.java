package entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.time.*;

//Student Entity
@Entity //This will let Java know that this is an entity that we are going to map to a database table.
//will mark a particular class as an entity bean, so it must have a no-argument constructor
@Table(name = "boarding_pass") //This is for the actual name of the database table name we are mapping to the class.
public class BoardingPassTrain {

    //Database Mapping
    @Id //This will map the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This is used with auto increment for your primary key.
    @Column(name = "id") //This is mapping the primary key to the id column in your database.
    private int id;

    @Column(name = "name") //This will map the name field to the column named first_name in your student table.
    private String name;

    @Column(name = "origin") //This will map the origin field to the column named email in your student table.
    private  String origin;

    @Column(name = "destination") //This will map the destination field to the column named email in your student table.
    private  String destination;

    @Column(name = "eta") //This will map the eta field to the column named email in your student table.
    private  Date eta;

    @Column(name = "departure") //This will map the departure field to the column named email in your student table.
    private  Date departure;

    @Column(name = "email") //This will map the email field to the column named email in your student table.
    private  String email;

    @Column(name = "phone") //This will map the phone field to the column named email in your student table.
    private  String phone;

    @Column(name = "gender") //This will map the gender field to the column named email in your student table.
    private  String gender;

    @Column(name = "age") //This will map the age field to the column named email in your student table.
    private  int age;

    @Column(name = "ticket_price") //This will map the ticket price field to the column named email in your student table.
    private float ticketPrice;

    //Constructors
    public BoardingPassTrain() {}

    //Constructors to set all the fields
    public BoardingPassTrain (String name, String origin, String destination, Date eta,
                             Date departure, String email, String phone, String gender, int age,
                             float ticketPrice) {
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.eta = eta;
        this.departure = departure;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.ticketPrice = ticketPrice;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    //To string method

    @Override
    public String toString() {
        return String.format("BoardingPassTrain{id=%d, name='%s', origin='%s', destination='%s', " +
                "eta='%s', departure='%s', email='%s', phone='%s', gender='%s', age='%d', ticketPrice='%s'}",
                id, name, origin, destination, eta, departure, email, phone, gender, age, ticketPrice);
    }
}


