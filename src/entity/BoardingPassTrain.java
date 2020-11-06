package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//Boarding Pass Entity
@Entity

@Table(name = "boarding_pass")
public class BoardingPassTrain {

    //Database Mapping
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "train_id")
    private int trainID;

    @Column(name = "name")
    private String name;

    @Column(name = "eta")
    private  Date eta;

    @Column(name = "email")
    private  String email;

    @Column(name = "phone")
    private  String phone;

    @Column(name = "gender")
    private  String gender;

    @Column(name = "age")
    private  int age;

    @Column(name = "ticket_price")
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


