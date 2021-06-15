package pl.akademia.api.client;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<ClientDTO> getAllClientsDTO() {
        return clientRepository
            .findAll()
            .stream()
            .map(clientMapper::from)
            .collect(Collectors.toList());
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createOrUpdateClient(Client client){
        client.setRegistrationDate(new java.sql.Date(new Date().getTime()));
        return clientRepository.save(client);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email);
    }

    public boolean checkUniqueEmail(Client client){
        return !(clientRepository.getAllEmails().contains(client.getEmail()));
    }

}
