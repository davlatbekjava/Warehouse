package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Input;
import uz.pdp.entity.InputProduct;
import uz.pdp.entity.Product;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.InputProductAddDto;
import uz.pdp.model.InputProductDto;
import uz.pdp.repository.InputProductRepository;
import uz.pdp.service.InputProductService;
import uz.pdp.service.InputService;
import uz.pdp.service.ProductService;

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
    public InputProductDto add(InputProductAddDto addDto) {
        Long inputId = addDto.getInputId();
        if (Utils.isEmpty(inputId)) {
            throw new RuntimeException("Input id should not be null!");
        }

        Long productId = addDto.getProductId();
        if (Utils.isEmpty(productId)) {
            throw new RuntimeException("Product id should not be null!");
        }

        Double amount = addDto.getAmount();
        if (Utils.isEmpty(amount)) {
            throw new RuntimeException("Amount should not be null!");
        }

        Double price = addDto.getPrice();
        if (Utils.isEmpty(price)) {
            throw new RuntimeException("Price should not be null!");
        }

        Input input = inputService.validate(inputId);
        Product product = productService.validate(productId);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(product);
        inputProduct.setInput(input);
        inputProduct.setAmount(amount);
        inputProduct.setPrice(price);
        inputProduct.setExpireDate(addDto.getExpireDate());

        InputProduct savedInputProduct = inputProductRepository.save(inputProduct);

        return mapstructMapper.toInputProductDto(savedInputProduct);
    }
}
