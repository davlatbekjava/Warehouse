package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Supplier;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.CurrencyAddDto;
import uz.pdp.model.CurrencyDto;
import uz.pdp.repository.CurrencyRepository;
import uz.pdp.service.CurrencyService;

import java.util.List;
import java.util.Optional;

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
        return  mapstructMapper.toCurrencyDto(activeCurrencies);
    }

    @Override
    public CurrencyDto add(CurrencyAddDto addDto) {
        String name = addDto.getName();

        if (Utils.isEmpty(name)){
            throw new RuntimeException("Currency name should not be null!");
        }
        Optional<Currency> currencyOptional = currencyRepository.findByName(name);
        if (currencyOptional.isPresent()){
            throw new RuntimeException("Currency name already exist!");
        }

        Currency currency = mapstructMapper.toCurrency(addDto);
        Currency savedCurrency = currencyRepository.save(currency);
        return mapstructMapper.toCurrencyDto(savedCurrency);
    }

    @Override
    public Currency validate(Long id) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isEmpty()){
            throw new RuntimeException("Currency id = " + id + ", not found!");
        }
        Currency currency = currencyOptional.get();
        if (!currency.getActive()){
            throw new RuntimeException("Currency id = " + id + ", is inactive!");
        }
        return currency;
    }

}
