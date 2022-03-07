package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Measurement;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.MeasurementAddDto;
import uz.pdp.model.MeasurementDto;

import java.util.List;

public interface MeasurementService {
    ResponseEntity<ApiResponse<List<MeasurementDto>>> getAll();

    ResponseEntity<ApiResponse<MeasurementDto>> add(MeasurementAddDto addDto);

    ResponseEntity<ApiResponse<Measurement>> validate(Long id);
}
