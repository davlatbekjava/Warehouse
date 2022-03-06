package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.SupplierAddDto;
import uz.pdp.model.SupplierDto;
import uz.pdp.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(value = "/get/all")
    private List<SupplierDto> getAll(){
        return supplierService.getAll();
    }

    @GetMapping(value = "/get/{id}")
    private SupplierDto get(@PathVariable(value = "id") Long id){
        return supplierService.get(id);
    }

    @PostMapping(value = "/add")
    private SupplierDto add(@RequestBody SupplierAddDto addDto){
        return supplierService.add(addDto);
    }

    @PutMapping(value = "/edit/{id}")
    private SupplierDto edit(@PathVariable(value = "id") Long id, @RequestBody SupplierAddDto addDto){
        return supplierService.edit(id, addDto);
    }
}
