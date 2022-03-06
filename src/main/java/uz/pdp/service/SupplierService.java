package uz.pdp.service;

import uz.pdp.entity.Supplier;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> getAll();

    SupplierDto get(Long id);

    Supplier validate(Long id);

    SupplierDto add(SupplierAddDto addDto);

    SupplierDto edit(Long id, SupplierAddDto addDto);
}
