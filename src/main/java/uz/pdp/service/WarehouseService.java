package uz.pdp.service;

import uz.pdp.entity.Warehouse;
import uz.pdp.model.WarehouseAddAddDto;
import uz.pdp.model.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    WarehouseDto add(WarehouseAddAddDto warehouseAddDto);

    boolean active(Warehouse warehouse);

    List<WarehouseDto> getList();
}
