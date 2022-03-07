package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.User;
import uz.pdp.entity.Warehouse;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.UserAddDto;
import uz.pdp.model.UserDto;
import uz.pdp.repository.UserRepository;
import uz.pdp.repository.WarehouseRepository;
import uz.pdp.service.UserService;
import uz.pdp.service.WarehouseService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static uz.pdp.model.ApiResponse.response;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapstructMapper mapstructMapper;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseService warehouseService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MapstructMapper mapstructMapper, WarehouseRepository warehouseRepository, WarehouseService warehouseService) {
        this.userRepository = userRepository;
        this.mapstructMapper = mapstructMapper;
        this.warehouseRepository = warehouseRepository;
        this.warehouseService = warehouseService;
    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> add(UserAddDto addDto) {
        Set<Warehouse> warehouses = new HashSet<>();

        for (Long warehouseId : addDto.getWarehouseIds()) {
            //warehouse id null emasligini tekshirish
            if (Utils.isEmpty(warehouseId)) {
                continue;
            }
            //bunday warehouse bazada borligini va active ligini tekshirish
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
            if (warehouseOptional.isPresent()) {
                if (warehouseService.active(warehouseOptional.get())) {
                    warehouses.add(warehouseOptional.get());
                }
            }
        }
        //bunday phone number bazada yo'qligini tekshirish
        Optional<User> userOptional = userRepository.findByPhoneNumber(addDto.getPhoneNumber());
        if (userOptional.isPresent()) {
            return response("User this phone number is already exist!", HttpStatus.FORBIDDEN);
        }
        User user = mapstructMapper.toUser(addDto);
        user.setCode(Utils.generateCode());
        user.setWarehouses(warehouses);
        User savedUser = userRepository.save(user);
        UserDto userDto = mapstructMapper.toUserDto(savedUser);
        return response(userDto);
    }
}
