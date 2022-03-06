package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.entity.Client;
import uz.pdp.entity.Supplier;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ClientAddDto;
import uz.pdp.model.ClientDto;
import uz.pdp.repository.ClientRepository;
import uz.pdp.service.ClientService;

import java.util.Optional;

@Service
public class ClierntServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MapstructMapper mapstructMapper;

    public ClierntServiceImpl(ClientRepository clientRepository, MapstructMapper mapstructMapper) {
        this.clientRepository = clientRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ClientDto add(ClientAddDto addDto) {
        String phoneNumber = addDto.getPhoneNumber();
        if (Utils.isEmpty(phoneNumber)){
            throw new RuntimeException("Client phone number should not be null!");
        }

        String name = addDto.getName();
        if (Utils.isEmpty(name)){
            throw new RuntimeException("Client name should not be null!");
        }

        Optional<Client> clientOptional = clientRepository.findByPhoneNumber(phoneNumber);
        if (clientOptional.isPresent()){
            throw new RuntimeException("Such phone number Client is already exist!");
        }

        Client client = mapstructMapper.toClient(addDto);

        Client savedClient = clientRepository.save(client);

        return mapstructMapper.toClientDto(savedClient);
    }

    @Override
    public Client validate(Long id) {
        Optional<Client> clientOptional =clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            throw new RuntimeException("Client id = " + id + ", not found!");
        }

        return clientOptional.get();
    }
}
