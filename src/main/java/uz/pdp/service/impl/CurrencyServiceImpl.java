package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Currency;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.CurrencyAddDto;
import uz.pdp.model.CurrencyDto;
import uz.pdp.repository.CurrencyRepository;
import uz.pdp.service.CurrencyService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, MapstructMapper mapstructMapper) {
        this.currencyRepository = currencyRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public List<CurrencyDto> getAll() {
        List<Currency> activeCurrencies = currencyRepository.findAllByActiveTrue();
        return mapstructMapper.toCurrencyDto(activeCurrencies);
    }

    @Override
    public ResponseEntity<ApiResponse<CurrencyDto>> add(CurrencyAddDto addDto) {

        Optional<Currency> currencyOptional = currencyRepository.findByName(addDto.getName());
        if (currencyOptional.isPresent()) {
            return response("Currency name already exist!", HttpStatus.FORBIDDEN);
        }

        Currency currency = mapstructMapper.toCurrency(addDto);
        Currency savedCurrency = currencyRepository.save(currency);
        CurrencyDto currencyDto = mapstructMapper.toCurrencyDto(savedCurrency);
        return response(currencyDto);
    }

    @Override
    public ResponseEntity<ApiResponse<Currency>> validate(Long id) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isEmpty()) {
            return response("Currency id = " + id + ", not found!", HttpStatus.NOT_FOUND);
        }
        Currency currency = currencyOptional.get();
        if (!currency.getActive()) {
            return response("Currency id = " + id + ", is inactive!", HttpStatus.FORBIDDEN);
        }
        return response(currency);
    }

}
