package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.InputAddDto;
import uz.pdp.model.InputDto;
import uz.pdp.service.InputService;

@RestController
@RequestMapping("/api/input")
public class InputController {

    private final InputService inputService;

    @Autowired
    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping(value = "/add")
    private InputDto add(@RequestBody InputAddDto addDto){
        return inputService.add(addDto);
    }
}
