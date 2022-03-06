package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.entity.Supplier;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;
import uz.pdp.repository.SupplierRepository;
import uz.pdp.service.SupplierService;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final MapstructMapper mapstructMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, MapstructMapper mapstructMapper) {
        this.supplierRepository = supplierRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public List<SupplierDto> getAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return mapstructMapper.toSupplierDto(suppliers);
    }

    @Override
    public SupplierDto get(Long id) {
        Supplier supplier = validate(id);

        return mapstructMapper.toSupplierDto(supplier);
    }

    @Override
    public Supplier validate(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isEmpty()){
            throw new RuntimeException("Supplier id = " + id + ", not found!");
        }
        Supplier supplier = supplierOptional.get();
        if (!supplier.getActive()){
            throw new RuntimeException("Supplier id = " + id + ", is inactive!");
        }
        return supplier;
    }

    @Override
    public SupplierDto add(SupplierAddDto addDto) {
        String phoneNumber = addDto.getPhoneNumber();
        if (Utils.isEmpty(phoneNumber)){
            throw new RuntimeException("Supplier phone number should not be null!");
        }

        String name = addDto.getName();
        if (Utils.isEmpty(name)){
            throw new RuntimeException("Supplier name should not be null!");
        }

        Optional<Supplier> supplierOptional = supplierRepository.findByPhoneNumber(phoneNumber);
        if (supplierOptional.isPresent()){
            throw new RuntimeException("Such phone number Supplier is already exist!");
        }

        Supplier supplier = mapstructMapper.toSupplier(addDto);
        Supplier savedSupplier = supplierRepository.save(supplier);

        return mapstructMapper.toSupplierDto(savedSupplier);
    }

    @Override
    public SupplierDto edit(Long id, SupplierAddDto addDto) {

        Supplier supplier = validate(id);

        String phoneNumber = addDto.getPhoneNumber();
        if (Utils.isEmpty(phoneNumber)){
            throw new RuntimeException("Supplier phone number should not be null!");
        }

        if (supplierRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)){
            throw new RuntimeException("Supplier this phone number already existed!");
        }

        String name = addDto.getName();
        if (Utils.isEmpty(name)){
            throw new RuntimeException("Supplier name should not be null!");
        }

        supplier.setPhoneNumber(phoneNumber);
        supplier.setName(name);
        Supplier savedSupplier = supplierRepository.save(supplier);

        return mapstructMapper.toSupplierDto(savedSupplier);
    }


}
