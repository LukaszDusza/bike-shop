package pl.akademia.api.client;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Client;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createOrUpdateClient(Client client) {
        client.setRegistrationDate(new java.sql.Date(new Date().getTime()));
        return clientRepository.save(client);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email);
    }

    public boolean checkUniqueEmail(Client client){
        return !(clientRepository.getAllEmails().contains(client.getEmail()));
    }

    @Transactional
    public int deleteClientById(Long id){
        return clientRepository.deleteClientById(id);
    }

}
