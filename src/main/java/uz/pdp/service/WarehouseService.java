package uz.pdp.service;

import uz.pdp.entity.Warehouse;
import uz.pdp.model.WarehouseAddDto;
import uz.pdp.model.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    WarehouseDto add(WarehouseAddDto warehouseAddDto);

    boolean active(Warehouse warehouse);

    List<WarehouseDto> getList();
}
