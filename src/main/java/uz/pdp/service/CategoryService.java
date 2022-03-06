package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Category;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    ResponseEntity<?> add(CategoryAddDto addDto);

    ResponseEntity<List<CategoryDto>> getParents();

    ResponseEntity<?> getChildren(Long id);

    ResponseEntity<?> validate(Long id);
}
