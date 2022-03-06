package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.CurrencyAddDto;
import uz.pdp.model.CurrencyDto;
import uz.pdp.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/get/all")
    private List<CurrencyDto> getAll(){
        return currencyService.getAll();
    }

    @PostMapping(value = "/add")
    private CurrencyDto add(@RequestBody CurrencyAddDto addDto){
        return currencyService.add(addDto);
    }

}
