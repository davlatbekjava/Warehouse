package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.ClientAddDto;
import uz.pdp.model.ClientDto;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;
import uz.pdp.service.ClientService;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/add")
    private ClientDto add(@RequestBody ClientAddDto addDto){
        return clientService.add(addDto);
    }
}
