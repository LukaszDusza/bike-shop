package pl.akademia.api.model;

import lombok.Data;


import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Client")
    private Long idClient;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "last_Name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "phone_Number", length = 9)
    private String phoneNumber;

    @Column(name = "registration_Date")
    private Date registrationDate;

   @OneToOne(cascade = CascadeType.REMOVE)
   @JoinColumn(name = "address_id")
   private Address address;

}