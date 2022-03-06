package uz.pdp.service;

import uz.pdp.entity.Currency;
import uz.pdp.model.CurrencyAddDto;
import uz.pdp.model.CurrencyDto;
import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAll();

    CurrencyDto add(CurrencyAddDto addDto);

    Currency validate(Long id);
}
