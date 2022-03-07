package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.WarehouseAddDto;
import uz.pdp.model.WarehouseDto;
import uz.pdp.service.WarehouseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<WarehouseDto>> add(@Valid @RequestBody WarehouseAddDto addDto) {
        return warehouseService.add(addDto);
    }

    @GetMapping(value = "/get/all")
    private ResponseEntity<ApiResponse<List<WarehouseDto>>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return warehouseService.getAll(pageable);
    }
}
