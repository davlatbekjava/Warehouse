package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.entity.Supplier;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;
import uz.pdp.repository.SupplierRepository;
import uz.pdp.service.SupplierService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final MapstructMapper mapstructMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, MapstructMapper mapstructMapper) {
        this.supplierRepository = supplierRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> add(SupplierAddDto addDto) {

        Optional<Supplier> supplierOptional = supplierRepository.findByPhoneNumber(addDto.getPhoneNumber());
        if (supplierOptional.isPresent()){
            return response("Such phone number Supplier is already exist!", HttpStatus.FORBIDDEN);
        }

        Supplier supplier = mapstructMapper.toSupplier(addDto);
        Supplier savedSupplier = supplierRepository.save(supplier);
        SupplierDto supplierDto = mapstructMapper.toSupplierDto(savedSupplier);
        return response(supplierDto);
    }

    @Override
    public ResponseEntity<ApiResponse<List<SupplierDto>>> getAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDto> supplierDtoList = mapstructMapper.toSupplierDto(suppliers);
        return response(supplierDtoList);
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> get(Long id) {
        ResponseEntity<ApiResponse<Supplier>> responseEntity = validate(id);
        if (responseEntity.getStatusCodeValue()!=200){
            return response(responseEntity.getBody().getErrorMessage(),responseEntity.getStatusCode());
        }
        Supplier supplier = responseEntity.getBody().getData();
        SupplierDto supplierDto = mapstructMapper.toSupplierDto(supplier);
        return response(supplierDto);
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> edit(Long id, SupplierAddDto addDto) {

        ResponseEntity<ApiResponse<Supplier>> responseEntity = validate(id);
        if (responseEntity.getStatusCodeValue()!=200){
            return response(responseEntity.getBody().getErrorMessage(), responseEntity.getStatusCode());
        }
        Supplier supplier = responseEntity.getBody().getData();

        if (supplierRepository.existsByPhoneNumberAndIdNot(addDto.getPhoneNumber(), id)){
            return response("Supplier this phone number already existed!",HttpStatus.FORBIDDEN);
        }

        supplier.setPhoneNumber(addDto.getPhoneNumber());
        supplier.setName(addDto.getName());
        Supplier savedSupplier = supplierRepository.save(supplier);
        SupplierDto supplierDto = mapstructMapper.toSupplierDto(savedSupplier);
        return response(supplierDto);
    }

    @Override
    public ResponseEntity<ApiResponse<Supplier>> validate(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isEmpty()){
            response("Supplier id = " + id + ", not found!",HttpStatus.NOT_FOUND);
        }
        Supplier supplier = supplierOptional.get();
        if (!supplier.getActive()){
            response("Supplier id = " + id + ", is inactive!",HttpStatus.FORBIDDEN);
        }
        return response(supplier);
    }


}
