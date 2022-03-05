package uz.pdp.service;

import uz.pdp.entity.Category;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto add(CategoryAddDto addDto);

    List<CategoryDto> getParents();

    List<CategoryDto> getChildren(Long id);

    Category validate(Long id);
}
