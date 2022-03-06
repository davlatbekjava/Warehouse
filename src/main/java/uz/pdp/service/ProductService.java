package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.Product;
import uz.pdp.model.ProductAddDto;
import uz.pdp.model.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    List<ProductDto> getAllByCategory(Long categoryId);

    List<ProductDto> getAllByMeasurement(Long measurementId);

    ResponseEntity<?> add(ProductAddDto addDto);

    Product validate(Long id);

    ProductDto get(Long id);
}
