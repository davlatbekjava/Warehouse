package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.entity.Measurement;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.MeasurementAddDto;
import uz.pdp.model.MeasurementDto;
import uz.pdp.repository.MeasurementRepository;
import uz.pdp.service.MeasurementService;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MapstructMapper mapstructMapper;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository, MapstructMapper mapstructMapper) {
        this.measurementRepository = measurementRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public List<MeasurementDto> getAll() {
        List<Measurement> measurementList = measurementRepository.findAllByActiveTrue();
        return mapstructMapper.toMeasurementDto(measurementList);
    }

    @Override
    public MeasurementDto add(MeasurementAddDto addDto) {
        //Measurement name null emasligini tekshirish

        if (Utils.isEmpty(addDto.getName())) {
            throw new RuntimeException("Measurement name should not be null!");
        } else {
            //Bunday nomli Measurement bazada yo'qligini tekshirish
            Optional<Measurement> measurementOptional = measurementRepository.findByName(addDto.getName());
            if (measurementOptional.isPresent()) {
                throw new RuntimeException("Such Measurement name already exist!");
            }
        }

        Measurement measurement = mapstructMapper.toMeasurement(addDto);
        Measurement savedMeasurement = measurementRepository.save(measurement);

        return mapstructMapper.toMeasurementDto(savedMeasurement);
    }

    @Override
    public Measurement validate(Long id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isEmpty()){
            throw new RuntimeException("Measurement id = " + id + ", not found!");
        }
        Measurement measurement = measurementOptional.get();
        if (!measurement.getActive()){
            throw new RuntimeException("Measurement id = " + id + ", is inactive!");
        }
        return measurement;
    }


}
