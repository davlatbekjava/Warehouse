package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;
import uz.pdp.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/add")
    private CategoryDto add(@RequestBody CategoryAddDto addDto){
        return categoryService.add(addDto);
    }

    @GetMapping(value = "/get/parents")
    private List<CategoryDto> getParents(){
        return categoryService.getParents();
    }

    @GetMapping(value = "/get/{id}/children")
    private List<CategoryDto> getChildren(@PathVariable(value = "id") Long id){
        return categoryService.getChildren(id);
    }
}
