package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.WarehouseAddAddDto;
import uz.pdp.model.WarehouseDto;
import uz.pdp.service.WarehouseService;

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
    private WarehouseDto add(@RequestBody WarehouseAddAddDto warehouseAddDto) {
        return warehouseService.add(warehouseAddDto);
    }

    @GetMapping(value = "/get/list")
    private List<WarehouseDto> getList() {
        return warehouseService.getList();
    }
}
