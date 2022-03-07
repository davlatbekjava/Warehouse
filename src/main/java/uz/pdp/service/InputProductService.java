package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.InputProductAddDto;
import uz.pdp.model.InputProductDto;
import uz.pdp.model.response.DailyInputProductReport;

import java.time.LocalDate;
import java.util.List;

public interface InputProductService {
    ResponseEntity<ApiResponse<InputProductDto>> add(InputProductAddDto addDto);

    ResponseEntity<ApiResponse<List<DailyInputProductReport>>> getDailyInputProducts(String date);
}

