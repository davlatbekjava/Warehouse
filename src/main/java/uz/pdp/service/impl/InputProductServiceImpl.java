package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Input;
import uz.pdp.entity.InputProduct;
import uz.pdp.entity.Product;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.InputProductAddDto;
import uz.pdp.model.InputProductDto;
import uz.pdp.model.response.DailyInputProductReport;
import uz.pdp.repository.InputProductRepository;
import uz.pdp.service.InputProductService;
import uz.pdp.service.InputService;
import uz.pdp.service.ProductService;

import java.time.LocalDate;
import java.util.List;

import static uz.pdp.model.ApiResponse.response;

@Service
public class InputProductServiceImpl implements InputProductService {

    private final InputProductRepository inputProductRepository;
    private final MapstructMapper mapstructMapper;
    private final ProductService productService;
    private final InputService inputService;

    @Autowired
    public InputProductServiceImpl(InputProductRepository inputProductRepository, MapstructMapper mapstructMapper, ProductService productService, InputService inputService) {
        this.inputProductRepository = inputProductRepository;
        this.mapstructMapper = mapstructMapper;
        this.productService = productService;
        this.inputService = inputService;
    }

    @Override
    public ResponseEntity<ApiResponse<InputProductDto>> add(InputProductAddDto addDto) {

        ResponseEntity<ApiResponse<Input>> inputResponse = inputService.validate(addDto.getInputId());
        if (inputResponse.getStatusCodeValue()!=200){
            return response(inputResponse.getBody().getErrorMessage(), inputResponse.getStatusCode());
        }
        Input input = inputResponse.getBody().getData();

        ResponseEntity<ApiResponse<Product>> productResponse = productService.validate(addDto.getProductId());
        if (productResponse.getStatusCodeValue()!=200){
            return response(productResponse.getBody().getErrorMessage(), productResponse.getStatusCode());
        }
        Product product = productResponse.getBody().getData();

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(product);
        inputProduct.setInput(input);
        inputProduct.setAmount(addDto.getAmount());
        inputProduct.setPrice(addDto.getPrice());
        inputProduct.setExpireDate(addDto.getExpireDate());

        InputProduct savedInputProduct = inputProductRepository.save(inputProduct);
        InputProductDto inputProductDto = mapstructMapper.toInputProductDto(savedInputProduct);
        return response(inputProductDto);
    }

    @Override
    public ResponseEntity<ApiResponse<List<DailyInputProductReport>>> getDailyInputProducts(String date) {
        try {
            LocalDate parse = LocalDate.parse(date);
            List<DailyInputProductReport> dailyInputProducts = inputProductRepository.getDailyInputProducts(parse);
            return response(dailyInputProducts);
        }catch (Exception e){
            return response("Wrong date format, required type -> (yyyy-mm-dd)", HttpStatus.BAD_REQUEST);
        }
    }
}
