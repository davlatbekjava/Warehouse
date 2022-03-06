package uz.pdp.service;

import uz.pdp.entity.Input;
import uz.pdp.model.InputAddDto;
import uz.pdp.model.InputDto;

public interface InputService {
    InputDto add(InputAddDto addDto);

    Input validate(Long id);
}
