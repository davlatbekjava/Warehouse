package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.model.ProductAddDto;
import uz.pdp.model.ProductDto;
import uz.pdp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/get/all")
    private List<ProductDto> getAll(){
        return productService.getAll();
    }

    @GetMapping(value = "/get/{id}")
    private ProductDto get(@PathVariable(value = "id") Long id){
        return productService.get(id);
    }

    @GetMapping(value = "/get/all/by-category/{id}")
    private List<ProductDto> getAllByCategory(@PathVariable(value = "id") Long categoryId){
        return productService.getAllByCategory(categoryId);
    }

    @GetMapping(value = "/get/all/by-measurement/{id}")
    private List<ProductDto> getAllByMeasurement(@PathVariable(value = "id") Long measurementId){
        return productService.getAllByMeasurement(measurementId);
    }

    @PostMapping(value = "/add")
    private ProductDto add(@RequestBody ProductAddDto addDto){
        return productService.add(addDto);
    }


}
