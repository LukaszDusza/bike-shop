package pl.akademia.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="last_name")
    private String lastName;

    @JsonProperty(value="phone_number")
    private String phoneNumber;

    @JsonProperty(value="registration_date")
    private String registrationDate;

    @JsonProperty(value="email")
    private String email;

    @JsonProperty(value="address")
    private String address;
}
