package pl.akademia.api.client;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name="client")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false, length = 15)
  private String name;

  @Column(name = "last_Name", nullable = false, length = 30)
  private String lastName;

  @Column(name = "email", nullable = false, length = 30)
  private String email;

  @Column(name = "phone_Number", length = 9)
  private String phoneNumber;

  @Column(name = "registration_Date", nullable = false)
  private Date registrationDate;

  @OneToOne(cascade = {CascadeType.ALL})
  private Address address;
}