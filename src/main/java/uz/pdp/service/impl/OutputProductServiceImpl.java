package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.entity.InputProduct;
import uz.pdp.entity.Output;
import uz.pdp.entity.OutputProduct;
import uz.pdp.entity.Product;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.OutputProductAddDto;
import uz.pdp.model.OutputProductDto;
import uz.pdp.repository.OutputProductRepository;
import uz.pdp.service.OutputProductService;
import uz.pdp.service.OutputService;
import uz.pdp.service.ProductService;

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
    public OutputProductDto add(OutputProductAddDto addDto) {
        Long outputId = addDto.getOutputId();
        if (Utils.isEmpty(outputId)) {
            throw new RuntimeException("Output id should not be null!");
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

        Output output = outputService.validate(outputId);
        Product product = productService.validate(productId);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(product);
        outputProduct.setOutput(output);
        outputProduct.setAmount(amount);
        outputProduct.setPrice(price);

        OutputProduct savedOutputProduct = outputProductRepository.save(outputProduct);

        return mapstructMapper.toOutputProductDto(savedOutputProduct);
    }
}
