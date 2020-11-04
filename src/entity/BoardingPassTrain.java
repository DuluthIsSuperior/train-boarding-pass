package entity;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "train_id")
    private int trainID;

    @Column(name = "name") //This will map the name field to the column named first_name in your student table.
    private String name;

    @Column(name = "eta") //This will map the eta field to the column named email in your student table.
    private  Date eta;

    @Column(name = "email") //This will map the email field to the column named email in your student table.
    private  String email;

    @Column(name = "phone") //This will map the phone field to the column named email in your student table.
    private  String phone;

    @Column(name = "gender") //This will map the gender field to the column named email in your student table.
    private  String gender;

    @Column(name = "age") //This will map the age field to the column named email in your student table.
    private  int age;

    @Column(name = "ticket_price") //This will map the ticket price field to the column named email in your student table.
    private BigDecimal ticketPrice;

    //Constructors
    public BoardingPassTrain() {}

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

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
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

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    @Override
    public String toString() {
        return String.format("BoardingPassTrain{id=%d, train_id=%d, name='%s', " +
                        "eta='%s', email='%s', phone='%s', gender='%s', age='%d', ticketPrice='%s'}",
                id, trainID, name, eta, email, phone, gender, age, ticketPrice);
    }

}


