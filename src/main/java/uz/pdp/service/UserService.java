package uz.pdp.service;

import uz.pdp.model.UserAddDto;
import uz.pdp.model.UserDto;

public interface UserService {
    UserDto add(UserAddDto addDto);
}
