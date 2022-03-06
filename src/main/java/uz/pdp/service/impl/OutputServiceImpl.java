package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.entity.*;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.OutputAddDto;
import uz.pdp.model.OutputDto;
import uz.pdp.repository.OutputRepository;
import uz.pdp.service.ClientService;
import uz.pdp.service.CurrencyService;
import uz.pdp.service.OutputService;
import uz.pdp.service.WarehouseService;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public OutputDto add(OutputAddDto addDto) {

        Long currencyId = addDto.getCurrencyId();
        Long clientId = addDto.getClientId();
        Long warehouseId = addDto.getWarehouseId();
        String factureNumber = addDto.getFactureNumber();

        if (Utils.isEmpty(currencyId)) {
            throw new RuntimeException("Currency id should not be null!");
        }

        if (Utils.isEmpty(clientId)) {
            throw new RuntimeException("Client id should not be null!");
        }

        if (Utils.isEmpty(warehouseId)) {
            throw new RuntimeException("Warehouse id should not be null!");
        }

        if (Utils.isEmpty(factureNumber)) {
            throw new RuntimeException("Facture Number should not be null!");
        }

        Currency currency = currencyService.validate(currencyId);
        Client client = clientService.validate(clientId);
        Warehouse warehouse = warehouseService.validate(warehouseId);

        Output output = new Output();
        output.setCode(Utils.generateCode());
        output.setCurrency(currency);
        output.setFactureNumber(factureNumber);
        output.setClient(client);
        output.setWarehouse(warehouse);
        output.setDate(LocalDateTime.now());

        Output savedOutput = outputRepository.save(output);
        return mapstructMapper.toOutputDto(savedOutput);
    }

    @Override
    public Output validate(Long id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isEmpty()){
            throw new RuntimeException("Output id = " + id + ", not found!");
        }

        return outputOptional.get();
    }
}
