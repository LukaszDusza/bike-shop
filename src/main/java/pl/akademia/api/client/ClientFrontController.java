package pl.akademia.api.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientFrontController {

    private ClientService clientService;

    public ClientFrontController(ClientService clientService){
        this.clientService=clientService;
    }

    @PostMapping("/add/client")
    public String addClient(@ModelAttribute Client client){
        clientService.createOrUpdateClient(client);
        return "form-client";
    }
}
