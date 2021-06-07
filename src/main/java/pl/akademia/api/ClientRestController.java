package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.model.Client;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    private final ClientService clientService;



    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.getAllClients();
        if(clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client){
        if(client.getIdClient() == null){
            if(clientService.checkUniqueEmail(client)){
                return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Client client = clientService.getClientById(id);
        if(client == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

//    @DeleteMapping("/clients/{id}/delete")
//    public ResponseEntity<Client> deleteById(@PathVariable Long id){
//        if(clientService.deleteClientById(id) == null){
//            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        }
//        return new ResponseEntity<>(clientService.deleteClientById(id), HttpStatus.OK);
//    }

}
