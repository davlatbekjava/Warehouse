package uz.pdp.service;

import uz.pdp.entity.Measurement;
import uz.pdp.model.MeasurementAddDto;
import uz.pdp.model.MeasurementDto;

import java.util.List;

public interface MeasurementService {
    List<MeasurementDto> getAll();

    MeasurementDto add(MeasurementAddDto addDto);

    Measurement validate(Long id);
}
