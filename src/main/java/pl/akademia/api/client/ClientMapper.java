package pl.akademia.api.client;

import org.springframework.stereotype.Component;
import pl.akademia.api.utils.DTOMapper;

@Component
public class ClientMapper implements DTOMapper<Client, ClientDTO>{


    @Override
    public ClientDTO from(Client from) {
        return ClientDTO
                .builder()
                .name(from.getName())
                .lastName(from.getLastName())
                .city(from.getAddress().getCity())
                .houseNumber(from.getAddress().getHouseNumber())
                .email(from.getEmail())
                .phoneNumber(from.getPhoneNumber())
                .registrationDate(from.getAddress().toString())
                .postalCode(from.getAddress().getPostalCode())
                .street(from.getAddress().getStreet())
                .build();
    }
}
