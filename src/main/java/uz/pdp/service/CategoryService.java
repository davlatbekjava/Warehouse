package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Category;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    ResponseEntity<ApiResponse<CategoryDto>> add(CategoryAddDto addDto);

    ResponseEntity<ApiResponse<List<CategoryDto>>> getParents();

    ResponseEntity<ApiResponse<List<CategoryDto>>> getChildren(Long id);

    ResponseEntity<ApiResponse<Category>> validate(Long id);
}
