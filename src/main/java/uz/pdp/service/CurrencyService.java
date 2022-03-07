package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.entity.Currency;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.CurrencyAddDto;
import uz.pdp.model.CurrencyDto;
import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAll();

    ResponseEntity<ApiResponse<CurrencyDto>> add(CurrencyAddDto addDto);

    ResponseEntity<ApiResponse<Currency>> validate(Long id);
}
