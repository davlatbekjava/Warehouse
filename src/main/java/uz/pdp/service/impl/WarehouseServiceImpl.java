package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Warehouse;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.WarehouseAddDto;
import uz.pdp.model.WarehouseDto;
import uz.pdp.repository.WarehouseRepository;
import uz.pdp.service.WarehouseService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

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
    public ResponseEntity<ApiResponse<WarehouseDto>> add(WarehouseAddDto addDto) {

        //Bunday nomli warehouse oldin bazada mavjudmasligini tekshirish
        Optional<Warehouse> warehouseOptional = warehouseRepository.findByName(addDto.getName());
        if (warehouseOptional.isPresent()) {
            return response("This warehouse name already exist", HttpStatus.FORBIDDEN);
        }

        Warehouse warehouse = mapstructMapper.toWarehouse(addDto);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        WarehouseDto warehouseDto = mapstructMapper.toWarehouseDto(savedWarehouse);
        return response(warehouseDto);
    }

    @Override
    public ResponseEntity<ApiResponse<List<WarehouseDto>>> getAll(Pageable pageable) {
        Page<Warehouse> warehousePage = warehouseRepository.findAll(pageable);
        List<Warehouse> warehouseList = warehousePage.getContent();
        List<WarehouseDto> warehouseDtoList = mapstructMapper.toWarehouseDto(warehouseList);
        return response(warehouseDtoList);
    }

    @Override
    public boolean active(Warehouse warehouse) {
        if (Utils.isEmpty(warehouse)) {
            return false;
        }
        return warehouse.getActive();
    }


    @Override
    public ResponseEntity<ApiResponse<Warehouse>> validate(Long id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if (warehouseOptional.isEmpty()) {
            return response("Warehouse id = " + id + ", not found!",HttpStatus.NOT_FOUND);
        }
        Warehouse warehouse = warehouseOptional.get();
        if (!warehouse.getActive()) {
            return response("Warehouse id = " + id + ", is inactive!",HttpStatus.FORBIDDEN);
        }
        return response(warehouse);
    }

}
