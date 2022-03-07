package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.UserAddDto;
import uz.pdp.model.UserDto;

public interface UserService {
    ResponseEntity<ApiResponse<UserDto>> add(UserAddDto addDto);
}
