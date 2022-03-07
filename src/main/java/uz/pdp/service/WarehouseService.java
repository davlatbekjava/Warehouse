package uz.pdp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Warehouse;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.WarehouseAddDto;
import uz.pdp.model.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    ResponseEntity<ApiResponse<WarehouseDto>> add(WarehouseAddDto addDto);

    boolean active(Warehouse warehouse);

    ResponseEntity<ApiResponse<List<WarehouseDto>>> getAll(Pageable pageable);

    ResponseEntity<ApiResponse<Warehouse>> validate(Long id);
}
