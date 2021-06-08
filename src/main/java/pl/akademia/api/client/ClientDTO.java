package pl.akademia.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class ClientDTO {

    //todo do uzupelnienia i nie jest to Encja, zwykla klasa czyli POJO
    //  "email": "jagodaz@interia.pl",
    //  "lastName": "Kowalska",
    //  "name": "Jagoda",
    //  "phoneNumber": "505412456",
    //  "registrationDate": "2021-06-07",
    //  "houseNumber": "105",
    //  "postalCode": "00205",
    //  "street": "Grunwaldzka"
    @JsonProperty(value="name")
    private String name;
    @JsonProperty(value="LastName")
    private  String lastName;
    @JsonProperty(value="phoneNumber")
    private  String phoneNumber;
    @JsonProperty(value="registrationDate")
    private LocalDate registrationDate;
    @JsonProperty(value="email")
    private String email;
    @JsonProperty(value="street")
    private String street;
    @JsonProperty(value="houseNumber")
    private String houseNumber;
    @JsonProperty(value="postalCode")
    private String postalCode;

    public ClientDTO(String name, String lastName, String phoneNumber,
                     LocalDate registrationDate, String email, String street,
                     String houseNumber, String postalCode) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
