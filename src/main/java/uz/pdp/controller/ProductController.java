package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.ProductAddDto;
import uz.pdp.model.ProductDto;
import uz.pdp.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<ProductDto>> add(@Valid @RequestBody ProductAddDto addDto) {
        return productService.add(addDto);
    }

    @GetMapping(value = "/get/all")
    private ResponseEntity<ApiResponse<List<ProductDto>>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return productService.getAll(pageable);
    }

    @GetMapping(value = "/get/{id}")
    private ResponseEntity<ApiResponse<ProductDto>> get(@PathVariable(value = "id") Long id) {
        return productService.get(id);
    }

    @GetMapping(value = "/get/all/by-category/{id}")
    private ResponseEntity<ApiResponse<List<ProductDto>>> getAllByCategory(
            @PathVariable(value = "id") Long categoryId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        Pageable pageable= PageRequest.of(page,size);
        return productService.getAllByCategory(categoryId,pageable);
    }


}
