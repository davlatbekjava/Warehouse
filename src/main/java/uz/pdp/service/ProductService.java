package uz.pdp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Product;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.ProductAddDto;
import uz.pdp.model.ProductDto;

import java.util.List;

public interface ProductService {

    ResponseEntity<ApiResponse<ProductDto>> add(ProductAddDto addDto);

    ResponseEntity<ApiResponse<List<ProductDto>>> getAll(Pageable pageable);

    ResponseEntity<ApiResponse<ProductDto>> get(Long id);
    ResponseEntity<ApiResponse<List<ProductDto>>> getAllByCategory(Long categoryId, Pageable pageable);

    ResponseEntity<ApiResponse<Product>> validate(Long id);



}
