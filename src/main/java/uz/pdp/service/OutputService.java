package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Input;
import uz.pdp.entity.Output;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.OutputAddDto;
import uz.pdp.model.OutputDto;

public interface OutputService {
    ResponseEntity<ApiResponse<OutputDto>> add(OutputAddDto addDto);

    ResponseEntity<ApiResponse<Output>> validate(Long id);
}
