package uz.pdp.service.impl;

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
    public CategoryDto add(CategoryAddDto addDto) {
        //Category name null emasligini tekshirish
        if (Utils.isEmpty(addDto.getName())) {
            throw new RuntimeException("Category name should not be null!");
        } else {
            //Bunday nomli Category bazada yo'qligini tekshirish
            Optional<Category> categoryOptional = categoryRepository.findByName(addDto.getName());
            if (categoryOptional.isPresent()) {
                throw new RuntimeException("Such Category name already exist!");
            }
        }

        //Parent Categoryni tekshirish
        Category parentCategory = null;

        if (!Utils.isEmpty(addDto.getParentCategoryId())) {
            Optional<Category> parentCategoryOptional = categoryRepository.findById(addDto.getParentCategoryId());
            if (parentCategoryOptional.isEmpty()) {
                throw new RuntimeException("Parent Category id = " + addDto.getParentCategoryId() + ", not found!");
            }
            parentCategory = parentCategoryOptional.get();
            if (!parentCategory.getActive()) {
                throw new RuntimeException("Parent Category id = " + addDto.getParentCategoryId() + ", is inactive!");
            }
        }

        Category category = mapstructMapper.toCategory(addDto);
        category.setParentCategory(parentCategory);

        Category savedCategory = categoryRepository.save(category);

        return mapstructMapper.toCategoryDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getParents() {
        List<Category> parents = categoryRepository.findAllByParentCategoryNullAndActiveTrue();
        return mapstructMapper.toCategoryDto(parents);
    }

    @Override
    public List<CategoryDto> getChildren(Long id) {
        Category parentCategory = validate(id);

        List<Category> children = categoryRepository.findAllByParentCategoryAndActiveTrue(parentCategory);

        return mapstructMapper.toCategoryDto(children);
    }

    @Override
    public Category validate(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()){
            throw new RuntimeException("Category id = " + id + ", not found!");
        }
        Category category = categoryOptional.get();
        if (!category.getActive()){
            throw new RuntimeException("Category id = " + id + ", is inactive!");
        }
        return category;
    }


}
