package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Warehouse;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.WarehouseAddDto;
import uz.pdp.model.WarehouseDto;
import uz.pdp.repository.WarehouseRepository;
import uz.pdp.service.WarehouseService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, MapstructMapper mapstructMapper) {
        this.warehouseRepository = warehouseRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public WarehouseDto add(WarehouseAddDto addDto) {
        String name=addDto.getName();

        //warehouse name null emasligini tekshirish
        if (Utils.isEmpty(name)){
            throw new RuntimeException("Warehouse name should not be null");
        }else {
            //Bunday nomli warehouse oldin bazada mavjudmasligini tekshirish
            Optional<Warehouse> warehouseOptional = warehouseRepository.findByName(name);
            if (warehouseOptional.isPresent()){
                throw new RuntimeException("This name warehouse already exist");
            }
        }

        Warehouse warehouse= mapstructMapper.toWarehouse(addDto);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        WarehouseDto warehouseDto= mapstructMapper.toWarehouseDto(savedWarehouse);

        return warehouseDto;
    }


    @Override
    public boolean active(Warehouse warehouse) {
        if (Utils.isEmpty(warehouse)){
            return false;
        }
        return warehouse.getActive();
    }


    @Override
    public List<WarehouseDto> getList() {
        List<Warehouse> activeWarehouses = warehouseRepository.findAllActiveWarehouses();
        return mapstructMapper.toWarehouseDto(activeWarehouses);
    }

}
