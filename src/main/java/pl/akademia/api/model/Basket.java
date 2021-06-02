package pl.akademia.api.model;

import lombok.Data;

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
    private long id;

    @Column(name = "total_price")
    private BigDecimal basketTotalPrice;


    @OneToMany  (mappedBy = "basket")
    private List<Bike> bikes = new ArrayList<>();


}
