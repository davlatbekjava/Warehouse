package uz.pdp.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.model.MeasurementAddDto;
import uz.pdp.model.MeasurementDto;
import uz.pdp.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/get/all")
    private List<MeasurementDto> getAll(){
        return measurementService.getAll();
    }

    @PostMapping("/add")
    private MeasurementDto add(@RequestBody MeasurementAddDto addDto){
        return measurementService.add(addDto);
    }
}
