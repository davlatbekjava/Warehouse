package uz.pdp.helper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.entity.*;
import uz.pdp.model.*;

import java.util.List;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    Warehouse toWarehouse(WarehouseAddAddDto addDto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

    List<WarehouseDto> toWarehouseDto(List<Warehouse> warehouses);

    User toUser(UserAddDto addDto);

    UserDto toUserDto(User user);

    Category toCategory(CategoryAddDto addDto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDto(List<Category> categories);

    MeasurementDto toMeasurementDto(Measurement measurement);

    List<MeasurementDto> toMeasurementDto(List<Measurement> measurement);

    Measurement toMeasurement(MeasurementAddDto addDto);

    List<ProductDto> toProductDto(List<Product> products);

    ProductDto toProductDto(Product product);

    Product toProduct(ProductAddDto addDto);

    AttachmentDto toAttachmentDto(Attachment attachment);
}
