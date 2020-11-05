package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int ID;

    public int getID() {
        return ID;
    }

    @Column(name = "origin", length = 20, nullable = false)
    private String origin;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Column(name = "destination", length = 20, nullable = false)
    private String destination;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "departure", nullable = false)
    private String departure;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    @Column(name = "distance", nullable = false)
    private BigDecimal distance;

    public BigDecimal getDistance() {
        return distance;
    }

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public Train() {}

    @Override
    public String toString() {
        return String.format("%d - to %s - leaves %s - $%s", ID, destination, departure, price.toString());
    }
}