package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "schedule")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @SuppressWarnings("unused")
    private int ID; // variable set by entity

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
    @SuppressWarnings("unused")
    private String destination; // variable is set by entity

    public String getDestination() {
        return destination;
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
    @SuppressWarnings("unused")
    private BigDecimal distance;    // variable is set by entity

    public BigDecimal getDistance() {
        return distance;
    }

    @Column(name = "price", nullable = false)
    @SuppressWarnings("unused")
    private BigDecimal price;   // variable is set by entity

    public BigDecimal getPrice() {
        return price;
    }

    public Train() {}

    @Override
    public String toString() {
        return String.format("%d - to %s - leaves %s - $%s", ID, destination, departure, price.toString());
    }
}