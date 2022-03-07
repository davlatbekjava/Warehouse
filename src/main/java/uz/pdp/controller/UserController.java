package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.UserAddDto;
import uz.pdp.model.UserDto;
import uz.pdp.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<UserDto>> add(@Valid @RequestBody UserAddDto addDto){
       return userService.add(addDto);
    }
}
