package uz.pdp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.OutputProductAddDto;
import uz.pdp.model.OutputProductDto;
import uz.pdp.service.OutputProductService;

@RestController
@RequestMapping(value = "/api/output-product")
public class OutputProductController {

    private final OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping(value = "/add")
    private OutputProductDto add(@RequestBody OutputProductAddDto addDto){
        return outputProductService.add(addDto);
    }




}
