package pl.akademia.api.client;

import org.springframework.stereotype.Component;
import pl.akademia.api.utils.DTOMapper;

@Component
public class ClientMapper implements DTOMapper<Client, ClientDTO> {

  @Override
  public ClientDTO from(Client from) {
    return ClientDTO
        .builder()
        .name(from.getName())
        .lastName(from.getLastName())
        .email(from.getEmail())
        .phoneNumber(from.getPhoneNumber())
        .registrationDate(from.getRegistrationDate().toString())
        .address(String.join("; ",
            from.getAddress().getStreet(),
            from.getAddress().getHouseNumber(),
            from.getAddress().getCity(),
            from.getAddress().getPostalCode()))
        .build();
  }
}