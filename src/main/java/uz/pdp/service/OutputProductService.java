package uz.pdp.service;

import uz.pdp.model.OutputProductAddDto;
import uz.pdp.model.OutputProductDto;

public interface OutputProductService {
    OutputProductDto add(OutputProductAddDto addDto);
}
