package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;
import uz.pdp.service.SupplierService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<SupplierDto>> add(@Valid @RequestBody SupplierAddDto addDto){
        return supplierService.add(addDto);
    }

    @GetMapping(value = "/get/all")
    private ResponseEntity<ApiResponse<List<SupplierDto>>> getAll(){
        return supplierService.getAll();
    }

    @GetMapping(value = "/get/{id}")
    private ResponseEntity<ApiResponse<SupplierDto>> get(@PathVariable(value = "id") Long id){
        return supplierService.get(id);
    }

    @PutMapping(value = "/edit/{id}")
    private ResponseEntity<ApiResponse<SupplierDto>> edit(@PathVariable(value = "id") Long id, @RequestBody SupplierAddDto addDto){
        return supplierService.edit(id, addDto);
    }
}
