package uz.pdp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.MeasurementAddDto;
import uz.pdp.model.MeasurementDto;
import uz.pdp.service.MeasurementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    private ResponseEntity<ApiResponse<MeasurementDto>> add(@Valid @RequestBody MeasurementAddDto addDto){
        return measurementService.add(addDto);
    }

    @GetMapping("/get/all")
    private ResponseEntity<ApiResponse<List<MeasurementDto>>> getAll(){
        return measurementService.getAll();
    }
}
