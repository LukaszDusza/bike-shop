package pl.akademia.api.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Controller
public class ClientFrontController {

    private final ClientService clientService;

    public ClientFrontController(ClientService clientService){
        this.clientService=clientService;
    }

    @GetMapping("/client")
    public String clientPage(){
        return "form-client";
    }

    @PostMapping("/add/client")
    public String addClient(@ModelAttribute Client client){
        System.out.println(client);
        client.setRegistrationDate(new java.sql.Date(Timestamp.valueOf(LocalDateTime.now()).getTime()));
        clientService.createOrUpdateClient(client);
        return "redirect:/client";
    }
}
