package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Input;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.InputAddDto;
import uz.pdp.model.InputDto;
import uz.pdp.repository.InputRepository;
import uz.pdp.service.CurrencyService;
import uz.pdp.service.InputService;
import uz.pdp.service.SupplierService;
import uz.pdp.service.WarehouseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InputServiceImpl implements InputService {

    private final InputRepository inputRepository;
    private final MapstructMapper mapstructMapper;
    private final CurrencyService currencyService;
    private final WarehouseService warehouseService;
    private final SupplierService supplierService;


    @Autowired
    public InputServiceImpl(InputRepository inputRepository, MapstructMapper mapstructMapper, CurrencyService currencyService, WarehouseService warehouseService, SupplierService supplierService) {
        this.inputRepository = inputRepository;
        this.mapstructMapper = mapstructMapper;
        this.currencyService = currencyService;
        this.warehouseService = warehouseService;
        this.supplierService = supplierService;
    }

    @Override
    public InputDto add(InputAddDto addDto) {
        Long currencyId = addDto.getCurrencyId();
        Long supplierId = addDto.getSupplierId();
        Long warehouseId = addDto.getWarehouseId();
        String factureNumber = addDto.getFactureNumber();

        if (Utils.isEmpty(currencyId)){
           throw new RuntimeException("Currency id should not be null!");
       }

        if (Utils.isEmpty(supplierId)){
            throw new RuntimeException("Supplier id should not be null!");
        }

        if (Utils.isEmpty(warehouseId)){
            throw new RuntimeException("Warehouse id should not be null!");
        }

        if (Utils.isEmpty(factureNumber)){
            throw new RuntimeException("Facture Number should not be null!");
        }

        Currency currency = currencyService.validate(currencyId);
        Supplier supplier = supplierService.validate(supplierId);
        Warehouse warehouse = warehouseService.validate(warehouseId);

        Input input=new Input();
        input.setCurrency(currency);
        input.setFactureNumber(factureNumber);
        input.setSupplier(supplier);
        input.setWarehouse(warehouse);
        input.setDate(LocalDateTime.now());

        Input savedInput = inputRepository.save(input);
        return mapstructMapper.toInputDto(savedInput);
    }

    @Override
    public Input validate(Long id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (inputOptional.isEmpty()){
            throw new RuntimeException("Category id = " + id + ", not found!");
        }
        return inputOptional.get();
    }
}
