package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Input;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
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

import static uz.pdp.model.ApiResponse.response;

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
    public ResponseEntity<ApiResponse<InputDto>> add(InputAddDto addDto) {

        ResponseEntity<ApiResponse<Currency>> currencyResponse = currencyService.validate(addDto.getCurrencyId());
        if (currencyResponse.getStatusCodeValue()!=200){
            return response(currencyResponse.getBody().getErrorMessage(),currencyResponse.getStatusCode());
        }
        Currency currency = currencyResponse.getBody().getData();

        ResponseEntity<ApiResponse<Supplier>> supplierResponse = supplierService.validate(addDto.getSupplierId());
        if (supplierResponse.getStatusCodeValue()!=200){
            return response(supplierResponse.getBody().getErrorMessage(),supplierResponse.getStatusCode());
        }
        Supplier supplier = supplierResponse.getBody().getData();

        ResponseEntity<ApiResponse<Warehouse>> warehouseResponse = warehouseService.validate(addDto.getWarehouseId());
        if (warehouseResponse.getStatusCodeValue()!=200){
            return response(warehouseResponse.getBody().getErrorMessage(),warehouseResponse.getStatusCode());
        }
        Warehouse warehouse = warehouseResponse.getBody().getData();

        Input input=new Input();
        input.setCurrency(currency);
        input.setFactureNumber(addDto.getFactureNumber());
        input.setSupplier(supplier);
        input.setWarehouse(warehouse);
        input.setDate(LocalDateTime.now());

        Input savedInput = inputRepository.save(input);
        InputDto inputDto = mapstructMapper.toInputDto(savedInput);
        return response(inputDto);
    }

    @Override
    public ResponseEntity<ApiResponse<Input>> validate(Long id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (inputOptional.isEmpty()){
            return response("Input id = " + id + ", not found!", HttpStatus.NOT_FOUND);
        }
        return response(inputOptional.get());
    }
}
