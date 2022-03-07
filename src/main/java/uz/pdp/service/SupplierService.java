package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Supplier;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;

import java.util.List;

public interface SupplierService {
    ResponseEntity<ApiResponse<SupplierDto>> add(SupplierAddDto addDto);

    ResponseEntity<ApiResponse<List<SupplierDto>>> getAll();

    ResponseEntity<ApiResponse<SupplierDto>> get(Long id);

    ResponseEntity<ApiResponse<Supplier>> validate(Long id);
    ResponseEntity<ApiResponse<SupplierDto>> edit(Long id, SupplierAddDto addDto);
}
