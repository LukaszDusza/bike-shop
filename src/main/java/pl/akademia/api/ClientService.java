package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Client;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;

    }

    public Client createOrUpdateClient(Client client){
        client.setRegistrationDate(new java.sql.Date(new Date().getTime()));
        addressRepository.save(client.getAddress());
        return clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAllClients();
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).orElse(null);
    }

//    @Transactional
//    public Client deleteClientById(Long id){
//        clientRepository.deleteById(id);
//        Client client = getClientById(id);
//        return client;
//    }

    public boolean checkUniqueEmail(Client client){
        return !(clientRepository.getAllEmails().contains(client.getEmail()));
    }
}

