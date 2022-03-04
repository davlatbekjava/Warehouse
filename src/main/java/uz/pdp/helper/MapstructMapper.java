package uz.pdp.helper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.entity.User;
import uz.pdp.entity.Warehouse;
import uz.pdp.model.UserAddDto;
import uz.pdp.model.UserDto;
import uz.pdp.model.WarehouseAddDto;
import uz.pdp.model.WarehouseDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    Warehouse toWarehouse(WarehouseAddDto addDto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

    List<WarehouseDto> toWarehouseDto(List<Warehouse> warehouses);

    User toUser(UserAddDto addDto);

    UserDto toUserDto(User user);


}
