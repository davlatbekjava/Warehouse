package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.CategoryAddDto;
import uz.pdp.model.CategoryDto;
import uz.pdp.service.CategoryService;

import javax.validation.Valid;
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
    private ResponseEntity<ApiResponse<CategoryDto>> add(@Valid @RequestBody CategoryAddDto addDto){
        return categoryService.add(addDto);
    }

    @GetMapping(value = "/get/parents")
    private ResponseEntity<ApiResponse<List<CategoryDto>>> getParents(){
        return categoryService.getParents();
    }

    @GetMapping(value = "/get/{id}/children")
    private ResponseEntity<ApiResponse<List<CategoryDto>>> getChildren(@PathVariable(value = "id") Long id){
        return categoryService.getChildren(id);
    }
}
