package uz.pdp.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.*;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.OutputProductAddDto;
import uz.pdp.model.OutputProductDto;
import uz.pdp.model.response.DailyInputProductReport;
import uz.pdp.model.response.DailyOutputProductReport;
import uz.pdp.repository.OutputProductRepository;
import uz.pdp.service.OutputProductService;
import uz.pdp.service.OutputService;
import uz.pdp.service.ProductService;

import java.time.LocalDate;
import java.util.List;

import static uz.pdp.model.ApiResponse.response;

@Service
public class OutputProductServiceImpl implements OutputProductService {

    private final OutputProductRepository outputProductRepository;
    private final MapstructMapper mapstructMapper;
    private final OutputService outputService;
    private final ProductService productService;

    public OutputProductServiceImpl(OutputProductRepository outputProductRepository, MapstructMapper mapstructMapper, OutputService outputService, ProductService productService) {
        this.outputProductRepository = outputProductRepository;
        this.mapstructMapper = mapstructMapper;
        this.outputService = outputService;
        this.productService = productService;
    }


    @Override
    public ResponseEntity<ApiResponse<OutputProductDto>> add(OutputProductAddDto addDto) {

        ResponseEntity<ApiResponse<Output>> outputResponse = outputService.validate(addDto.getOutputId());
        if (outputResponse.getStatusCodeValue()!=200){
            return response(outputResponse.getBody().getErrorMessage(), outputResponse.getStatusCode());
        }
        Output output = outputResponse.getBody().getData();

        ResponseEntity<ApiResponse<Product>> productResponse = productService.validate(addDto.getProductId());
        if (productResponse.getStatusCodeValue()!=200){
            return response(productResponse.getBody().getErrorMessage(), productResponse.getStatusCode());
        }
        Product product = productResponse.getBody().getData();

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(product);
        outputProduct.setOutput(output);
        outputProduct.setAmount(addDto.getAmount());
        outputProduct.setPrice(addDto.getPrice());

        OutputProduct savedOutputProduct = outputProductRepository.save(outputProduct);
        OutputProductDto outputProductDto = mapstructMapper.toOutputProductDto(savedOutputProduct);
        return response(outputProductDto);
    }

    @Override
    public ResponseEntity<ApiResponse<List<DailyOutputProductReport>>> getDailyOutputProducts(String date) {
        try {
            LocalDate parse = LocalDate.parse(date);
            List<DailyOutputProductReport> dailyOutputProducts = outputProductRepository.getDailyOutputProducts(parse);
            return response(dailyOutputProducts);
        }catch (Exception e){
            return response("Wrong date format, required type -> (yyyy-mm-dd)", HttpStatus.BAD_REQUEST);
        }
    }
}
