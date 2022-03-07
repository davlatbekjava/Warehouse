package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Client;
import uz.pdp.entity.Supplier;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.ClientAddDto;
import uz.pdp.model.ClientDto;
import uz.pdp.repository.ClientRepository;
import uz.pdp.service.ClientService;
import java.util.Optional;
import static uz.pdp.model.ApiResponse.response;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MapstructMapper mapstructMapper;

    public ClientServiceImpl(ClientRepository clientRepository, MapstructMapper mapstructMapper) {
        this.clientRepository = clientRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<ClientDto>> add(ClientAddDto addDto) {

        Optional<Client> clientOptional = clientRepository.findByPhoneNumber(addDto.getPhoneNumber());
        if (clientOptional.isPresent()){
            return response("Such Client phone number is already exist!", HttpStatus.FORBIDDEN);
        }

        Client client = mapstructMapper.toClient(addDto);
        Client savedClient = clientRepository.save(client);
        ClientDto clientDto = mapstructMapper.toClientDto(savedClient);
        return response(clientDto);
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> validate(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            response("Client id = " + id + ", not found!",HttpStatus.NOT_FOUND);
        }
        Client client = clientOptional.get();
        if (!client.getActive()){
            response("Client id = " + id + ", is inactive!",HttpStatus.FORBIDDEN);
        }
        return response(client);
    }
}
