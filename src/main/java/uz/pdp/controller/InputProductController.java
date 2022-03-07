package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.InputProductAddDto;
import uz.pdp.model.InputProductDto;
import uz.pdp.model.response.DailyInputProductReport;
import uz.pdp.service.InputProductService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/input-product")
public class InputProductController {

    private final InputProductService inputProductService;

    @Autowired
    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<InputProductDto>> add(@Valid @RequestBody InputProductAddDto addDto){
        return inputProductService.add(addDto);
    }

    @GetMapping(value = "/get/date")
    private ResponseEntity<ApiResponse<List<DailyInputProductReport>>> getDailyInputProducts(@RequestParam String date){
        return inputProductService.getDailyInputProducts(date);
    }

}
