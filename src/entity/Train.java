package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int ID;

    @Column(name = "destination", length = 20, nullable = false)
    private String destination;

    @Column(name = "departure", nullable = false)
    private Date departure;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Train() {}

    @Override
    public String toString() {
        return String.format("%d - to %s - leaves %s - $%s", ID, destination, departure, price.toString());
    }
}