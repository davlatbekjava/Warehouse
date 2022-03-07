package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.OutputProductAddDto;
import uz.pdp.model.OutputProductDto;
import uz.pdp.model.response.DailyOutputProductReport;

import java.util.List;

public interface OutputProductService {
    ResponseEntity<ApiResponse<OutputProductDto>> add(OutputProductAddDto addDto);

    ResponseEntity<ApiResponse<List<DailyOutputProductReport>>> getDailyOutputProducts(String date);
}
