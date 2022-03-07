package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.service.CategoryService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapstructMapper mapstructMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapstructMapper mapstructMapper) {
        this.categoryRepository = categoryRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryDto>> add(CategoryAddDto addDto) {

        //Bunday nomli Category bazada yo'qligini tekshirish
        Optional<Category> categoryOptional = categoryRepository.findByName(addDto.getName());
        if (categoryOptional.isPresent()) {
            return response("Such Category name already exist", HttpStatus.FORBIDDEN);
        }

        //Parent Categoryni tekshirish
        Category parentCategory = null;

        Long parentCategoryId = addDto.getParentCategoryId();

        if (!Utils.isEmpty(parentCategoryId)) {
            ResponseEntity<ApiResponse<Category>> responseEntity = validate(parentCategoryId);

            if (responseEntity.getStatusCodeValue() != 200) {
                return response(responseEntity.getBody().getErrorMessage(), responseEntity.getStatusCode());
            }
            parentCategory = responseEntity.getBody().getData();
        }

        Category category = mapstructMapper.toCategory(addDto);
        category.setParentCategory(parentCategory);

        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto = mapstructMapper.toCategoryDto(savedCategory);

        return response(categoryDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getParents() {
        List<Category> parents = categoryRepository.findAllByParentCategoryNull();
        List<CategoryDto> categoryDtoList = mapstructMapper.toCategoryDto(parents);
        return response(categoryDtoList);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getChildren(Long id) {
        ResponseEntity<ApiResponse<Category>> responseEntity = validate(id);

        if (responseEntity.getStatusCodeValue() != 200) {
            return response(responseEntity.getBody().getErrorMessage(), responseEntity.getStatusCode());
        }
        Category parentCategory = responseEntity.getBody().getData();

        List<Category> children = categoryRepository.findAllByParentCategory(parentCategory);
        List<CategoryDto> categoryDtoList = mapstructMapper.toCategoryDto(children);
        return response(categoryDtoList);
    }

    @Override
    public ResponseEntity<ApiResponse<Category>> validate(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return response("Parent Category id = " + id + ", not found!", HttpStatus.NOT_FOUND);
        }
        Category category = categoryOptional.get();
        if (!category.getActive()) {
            return response("Parent Category id = " + id + ", is inactive!", HttpStatus.FORBIDDEN);
        }
        return response(category);
    }


}
