package uz.pdp.service;

import uz.pdp.entity.Client;
import uz.pdp.model.ClientAddDto;
import uz.pdp.model.ClientDto;

public interface ClientService {
    Client validate(Long id);

    ClientDto add(ClientAddDto addDto);
}
