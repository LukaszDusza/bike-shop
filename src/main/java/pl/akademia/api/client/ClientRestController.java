package pl.akademia.api.client;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients,HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client){
        if(client.getId() == null){
                return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.OK);
        }


    @GetMapping("/clients/dto")
    public ResponseEntity<List<ClientDTO>> getClientsDTO() {
        List<ClientDTO> clients = clientService.getAllClientsDTO();
        if (clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
