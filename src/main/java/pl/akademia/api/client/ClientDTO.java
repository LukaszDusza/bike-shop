package pl.akademia.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="last_name")
    private  String lastName;

    @JsonProperty(value="phone_number")
    private  String phoneNumber;

    @JsonProperty(value="registration_date")
    private LocalDate registrationDate;

    @JsonProperty(value="email")
    private String email;

    @JsonProperty(value="street")
    private String street;

    @JsonProperty(value="house_number")
    private String houseNumber;

    @JsonProperty(value="postal_code")
    private String postalCode;

    @JsonProperty(value="city")
    private String city;

}
