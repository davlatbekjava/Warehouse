package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.ClientAddDto;
import uz.pdp.model.ClientDto;
import uz.pdp.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<ClientDto>> add(@Valid @RequestBody ClientAddDto addDto){
        return clientService.add(addDto);
    }
}
