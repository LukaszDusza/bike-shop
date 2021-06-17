package pl.akademia.api.client;


import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akademia.api.exceptions.ClientNotFoundException;

import java.sql.DataTruncation;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients(@RequestParam(required = false) String email) {
        if(email != null) {
            Client client = clientService.getClientByEmail(email);
            if (client == null) {
                throw new ClientNotFoundException("Client not found");
            }
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()){
            throw new ClientNotFoundException("List of clients is empty");
        }
        return new ResponseEntity<>(clients,HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            throw new ClientNotFoundException("Client not found");
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client) {
        if(client.getId() == null) {
            return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.OK);
    }

    @GetMapping("/clients/dto")
    public ResponseEntity<List<ClientDTO>> getClientsDTO() {
        List<ClientDTO> clients = clientService.getAllClientsDTO();
        if (clients.isEmpty()){
            throw new ClientNotFoundException("List of clients is empty");
        }
        return new ResponseEntity<>(clients,HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}/delete")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(clientService.deleteClientById(id) > 0){
            return new ResponseEntity<>(clientService.deleteClientById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
