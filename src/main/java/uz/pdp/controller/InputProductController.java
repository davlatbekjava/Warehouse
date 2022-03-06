package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.InputProductAddDto;
import uz.pdp.model.InputProductDto;
import uz.pdp.service.InputProductService;

@RestController
@RequestMapping(value = "/api/input-product")
public class InputProductController {

    private final InputProductService inputProductService;

    @Autowired
    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping(value = "/add")
    private InputProductDto add(@RequestBody InputProductAddDto addDto){
        return inputProductService.add(addDto);
    }

}
