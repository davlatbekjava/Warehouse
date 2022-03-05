package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.Attachment;
import uz.pdp.entity.Category;
import uz.pdp.entity.Measurement;
import uz.pdp.entity.Product;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.AttachmentDto;
import uz.pdp.model.ProductAddDto;
import uz.pdp.model.ProductDto;
import uz.pdp.repository.ProductRepository;
import uz.pdp.service.AttachmentService;
import uz.pdp.service.CategoryService;
import uz.pdp.service.MeasurementService;
import uz.pdp.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapstructMapper mapstructMapper;
    private final CategoryService categoryService;
    private final MeasurementService measurementService;
    private final AttachmentService attachmentService;

    public ProductServiceImpl(ProductRepository productRepository, MapstructMapper mapstructMapper, CategoryService categoryService, MeasurementService measurementService, AttachmentService attachmentService) {
        this.productRepository = productRepository;
        this.mapstructMapper = mapstructMapper;
        this.categoryService = categoryService;
        this.measurementService = measurementService;
        this.attachmentService = attachmentService;
    }

    @Override
    public ProductDto get(Long id) {
        Product product = validate(id);
        return mapstructMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> activeProducts = productRepository.findAllByActiveTrue();
        return mapstructMapper.toProductDto(activeProducts);
    }

    @Override
    public List<ProductDto> getAllByCategory(Long categoryId) {
        List<Product> activeProductsByCategory = productRepository.findAllByCategoryIdAndActiveTrue(categoryId);
        return mapstructMapper.toProductDto(activeProductsByCategory);
    }

    @Override
    public List<ProductDto> getAllByMeasurement(Long measurementId) {
        List<Product> activeProductsByMeasurement = productRepository.findAllByMeasurementIdAndActiveTrue(measurementId);
        return mapstructMapper.toProductDto(activeProductsByMeasurement);
    }

    @Override
    public ProductDto add(ProductAddDto addDto) {
        //Product name ni tekshirish
        if (Utils.isEmpty(addDto.getName())){
            throw new RuntimeException("Product name should not be null");
        }else {
            //Bunday nomli product oldin bazada mavjudmasligini tekshirish
            Optional<Product> productOptional = productRepository.findByName(addDto.getName());
            if (productOptional.isPresent()){
                throw new RuntimeException("This name warehouse already exist");
            }
        }

        Category category = categoryService.validate(addDto.getCategoryId());
        Measurement measurement = measurementService.validate(addDto.getMeasurementId());
        Attachment attachment = attachmentService.validate(addDto.getAttachmentId());

        Product product = mapstructMapper.toProduct(addDto);
        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setCode(Utils.generateCode());
        product.setAttachment(attachment);
        product.setActive(true);

        Product savedProduct = productRepository.save(product);
        return mapstructMapper.toProductDto(savedProduct);
    }

    @Override
    public Product validate(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new RuntimeException("Product id = " + id + ", not found!");
        }
        Product product = productOptional.get();
        if (!product.getActive()){
            throw new RuntimeException("Product id = " + id + ", is inactive!");
        }
        return product;
    }


}
