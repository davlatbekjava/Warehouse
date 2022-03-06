package uz.pdp.helper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    CurrencyDto toCurrencyDto(Currency currency);

    List<CurrencyDto> toCurrencyDto(List<Currency> currencies);

    Currency toCurrency(CurrencyAddDto addDto);

    List<SupplierDto> toSupplierDto(List<Supplier> suppliers);

    SupplierDto toSupplierDto(Supplier supplier);

    Supplier toSupplier(SupplierAddDto addDto);

    @Mapping(source = "currency.name",target = "currency")
    InputDto toInputDto(Input input);

    List<InputDto> toInputDto(List<Input> input);


    InputProductDto toInputProductDto(InputProduct inputProduct);
}
