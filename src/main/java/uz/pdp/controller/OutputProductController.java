package uz.pdp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.OutputProductAddDto;
import uz.pdp.model.OutputProductDto;
import uz.pdp.model.response.DailyOutputProductReport;
import uz.pdp.service.OutputProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/output-product")
public class OutputProductController {

    private final OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<OutputProductDto>> add(@Valid @RequestBody OutputProductAddDto addDto){
        return outputProductService.add(addDto);
    }

    @GetMapping(value = "/get/date")
    private ResponseEntity<ApiResponse<List<DailyOutputProductReport>>> getDailyOutputProducts(@RequestParam String date){
      return outputProductService.getDailyOutputProducts(date);
    }




}
