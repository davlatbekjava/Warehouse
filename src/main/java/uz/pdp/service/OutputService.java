package uz.pdp.service;

import uz.pdp.entity.Output;
import uz.pdp.model.OutputAddDto;
import uz.pdp.model.OutputDto;

public interface OutputService {
    OutputDto add(OutputAddDto addDto);

    Output validate(Long id);
}
