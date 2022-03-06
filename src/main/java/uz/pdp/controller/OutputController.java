package uz.pdp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.model.OutputAddDto;
import uz.pdp.model.OutputDto;
import uz.pdp.service.OutputService;

@RestController
@RequestMapping(value = "/api/output")
public class OutputController {

    private final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping(value = "/add")
    private OutputDto add(@RequestBody OutputAddDto addDto){
       return outputService.add(addDto);
    }


}
