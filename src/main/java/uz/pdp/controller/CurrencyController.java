package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.CurrencyAddDto;
import uz.pdp.model.CurrencyDto;
import uz.pdp.service.CurrencyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<CurrencyDto>> add(@Valid @RequestBody CurrencyAddDto addDto){
        return currencyService.add(addDto);
    }

    @GetMapping(value = "/get/all")
    private List<CurrencyDto> getAll(){
        return currencyService.getAll();
    }



}
