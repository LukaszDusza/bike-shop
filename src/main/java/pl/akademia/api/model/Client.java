package pl.akademia.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


@Data
@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Client")
    private Long idClient;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "last_Name", nullable=false, length = 30)
    private String lastName;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "phone_Number", length = 9)
    private String phoneNumber;

    @Column(name = "registration_Date", nullable = false)
    private LocalDate registrationDate;

//    @Column(name = "orders", nullable = false)
//    private List<Order> = new ArrayList<Order>();


}
