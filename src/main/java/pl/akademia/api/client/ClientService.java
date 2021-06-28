package pl.akademia.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        logger.debug("Return {} clients.", clients.size());
        return clients;
    }

    public List<ClientDTO> getAllClientsDTO() {
        List<ClientDTO> clientDTOs = clientRepository
            .findAll()
            .stream()
            .map(clientMapper::from)
            .collect(Collectors.toList());
        logger.info("Return {} clientDTOs.", clientDTOs.size());
        return clientDTOs;
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createOrUpdateClient(Client client){
        return clientRepository.save(client);
    }

    public Client getClientByEmail(String email) {
        logger.info("Return Client with email: {}", email);
        return clientRepository.getClientByEmail(email);
    }


    @Transactional
    public int deleteClientById(Long id){
        return clientRepository.deleteClientById(id);

    }

}
