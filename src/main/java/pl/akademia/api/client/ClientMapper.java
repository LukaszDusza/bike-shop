package pl.akademia.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.akademia.api.utils.DTOMapper;

@Component
public class ClientMapper implements DTOMapper<Client, ClientDTO> {

  private final static Logger logger = LoggerFactory.getLogger(ClientMapper.class);


  @Override
  public ClientDTO from(Client from) {
    logger.info("Client entity {}", from.toString());
    ClientDTO clientDTO = ClientDTO
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
    logger.info("Created ClientDTO: {}", clientDTO.toString());
    return clientDTO;
  }

}