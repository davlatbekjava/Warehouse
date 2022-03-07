package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Input;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.InputAddDto;
import uz.pdp.model.InputDto;

public interface InputService {
    ResponseEntity<ApiResponse<InputDto>> add(InputAddDto addDto);

    ResponseEntity<ApiResponse<Input>> validate(Long id);
}
