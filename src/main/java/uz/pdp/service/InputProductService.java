package uz.pdp.service;

import uz.pdp.model.InputProductAddDto;
import uz.pdp.model.InputProductDto;

public interface InputProductService {
    InputProductDto add(InputProductAddDto addDto);
}
