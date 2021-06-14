package pl.akademia.api.order;

import lombok.Data;
import pl.akademia.api.bike.Bike;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_price")
    private BigDecimal basketTotalPrice;

    @ManyToMany
    private List<Bike> bikes = new ArrayList<>();
}