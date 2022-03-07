package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Client;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.ClientAddDto;
import uz.pdp.model.ClientDto;

public interface ClientService {
    ResponseEntity<ApiResponse<Client>> validate(Long id);

    ResponseEntity<ApiResponse<ClientDto>> add(ClientAddDto addDto);
}
