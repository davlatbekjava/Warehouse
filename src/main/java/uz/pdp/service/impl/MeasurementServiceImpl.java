package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Measurement;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.MeasurementAddDto;
import uz.pdp.model.MeasurementDto;
import uz.pdp.repository.MeasurementRepository;
import uz.pdp.service.MeasurementService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MapstructMapper mapstructMapper;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository, MapstructMapper mapstructMapper) {
        this.measurementRepository = measurementRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<MeasurementDto>> add(MeasurementAddDto addDto) {
        //Bunday nomli Measurement bazada yo'qligini tekshirish
        Optional<Measurement> measurementOptional = measurementRepository.findByName(addDto.getName());
        if (measurementOptional.isPresent()) {
            response("Such Measurement name already exist!", HttpStatus.FORBIDDEN);
        }

        Measurement measurement = mapstructMapper.toMeasurement(addDto);
        Measurement savedMeasurement = measurementRepository.save(measurement);
        MeasurementDto measurementDto = mapstructMapper.toMeasurementDto(savedMeasurement);

        return response(measurementDto);
    }

    @Override
    public ResponseEntity<ApiResponse<List<MeasurementDto>>> getAll() {
        List<Measurement> measurementList = measurementRepository.findAllByActiveTrue();
        List<MeasurementDto> measurementDtoList = mapstructMapper.toMeasurementDto(measurementList);
        return response(measurementDtoList);
    }

    @Override
    public ResponseEntity<ApiResponse<Measurement>> validate(Long id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isEmpty()) {
            response("Measurement id = " + id + ", not found!", HttpStatus.NOT_FOUND);
        }
        Measurement measurement = measurementOptional.get();
        if (!measurement.getActive()) {
            response("Measurement id = " + id + ", is inactive!", HttpStatus.FORBIDDEN);
        }
        return response(measurement);
    }


}
