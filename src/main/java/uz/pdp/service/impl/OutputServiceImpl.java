package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.*;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.OutputAddDto;
import uz.pdp.model.OutputDto;
import uz.pdp.repository.OutputRepository;
import uz.pdp.service.ClientService;
import uz.pdp.service.CurrencyService;
import uz.pdp.service.OutputService;
import uz.pdp.service.WarehouseService;

import java.time.LocalDateTime;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

@Service
public class OutputServiceImpl implements OutputService {

    private final OutputRepository outputRepository;
    private final MapstructMapper mapstructMapper;
    private final CurrencyService currencyService;
    private final WarehouseService warehouseService;
    private final ClientService clientService;

    public OutputServiceImpl(OutputRepository outputRepository, MapstructMapper mapstructMapper, CurrencyService currencyService, WarehouseService warehouseService, ClientService clientService) {
        this.outputRepository = outputRepository;
        this.mapstructMapper = mapstructMapper;
        this.currencyService = currencyService;
        this.warehouseService = warehouseService;
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ApiResponse<OutputDto>> add(OutputAddDto addDto) {

        ResponseEntity<ApiResponse<Currency>> currencyResponse = currencyService.validate(addDto.getCurrencyId());
        if (currencyResponse.getStatusCodeValue()!=200){
            return response(currencyResponse.getBody().getErrorMessage(),currencyResponse.getStatusCode());
        }
        Currency currency = currencyResponse.getBody().getData();

        ResponseEntity<ApiResponse<Client>> clientResponse = clientService.validate(addDto.getClientId());
        if (clientResponse.getStatusCodeValue()!=200){
            return response(clientResponse.getBody().getErrorMessage(),clientResponse.getStatusCode());
        }
        Client client = clientResponse.getBody().getData();

        ResponseEntity<ApiResponse<Warehouse>> warehouseResponse = warehouseService.validate(addDto.getWarehouseId());
        if (warehouseResponse.getStatusCodeValue()!=200){
            return response(warehouseResponse.getBody().getErrorMessage(),warehouseResponse.getStatusCode());
        }
        Warehouse warehouse = warehouseResponse.getBody().getData();

        Output output = new Output();
        output.setCode(Utils.generateCode());
        output.setCurrency(currency);
        output.setFactureNumber(addDto.getFactureNumber());
        output.setClient(client);
        output.setWarehouse(warehouse);
        output.setDate(LocalDateTime.now());

        Output savedOutput = outputRepository.save(output);
        OutputDto outputDto = mapstructMapper.toOutputDto(savedOutput);
        return response(outputDto);
    }

    @Override
    public ResponseEntity<ApiResponse<Output>> validate(Long id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isEmpty()){
            return response("Output id = " + id + ", not found!", HttpStatus.NOT_FOUND);
        }
        return response(outputOptional.get());
    }
}
