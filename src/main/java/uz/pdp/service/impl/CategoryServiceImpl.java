package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapstructMapper mapstructMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapstructMapper mapstructMapper) {
        this.categoryRepository = categoryRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<?> add(CategoryAddDto addDto) {

        //Bunday nomli Category bazada yo'qligini tekshirish
        Optional<Category> categoryOptional = categoryRepository.findByName(addDto.getName());
        if (categoryOptional.isPresent()) {
            return new ResponseEntity<>("Such Category name already exist", HttpStatus.FORBIDDEN);
        }

        //Parent Categoryni tekshirish
        Category parentCategory = null;

        Long parentCategoryId = addDto.getParentCategoryId();

        if (!Utils.isEmpty(parentCategoryId)) {
            ResponseEntity<?> responseEntity = validate(parentCategoryId);
            if (responseEntity.getStatusCodeValue()!=200){
                return responseEntity;
            }
            parentCategory = (Category) responseEntity.getBody();
        }

        Category category = mapstructMapper.toCategory(addDto);
        category.setParentCategory(parentCategory);

        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto = mapstructMapper.toCategoryDto(savedCategory);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getParents() {
        List<Category> parents = categoryRepository.findAllByParentCategoryNull();
        List<CategoryDto> categoryDtoList = mapstructMapper.toCategoryDto(parents);
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getChildren(Long id) {
        ResponseEntity<?> responseEntity = validate(id);

        if (responseEntity.getStatusCodeValue()!=200){
            return responseEntity;
        }
        Category parentCategory = (Category) responseEntity.getBody();
        List<Category> children = categoryRepository.findAllByParentCategory(parentCategory);
        List<CategoryDto> categoryDtoList = mapstructMapper.toCategoryDto(children);
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> validate(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>("Parent Category id = " + id + ", not found!", HttpStatus.NOT_FOUND);
        }
        Category category = categoryOptional.get();
        if (!category.getActive()) {
            return new ResponseEntity<>("Parent Category id = " + id + ", is inactive!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
