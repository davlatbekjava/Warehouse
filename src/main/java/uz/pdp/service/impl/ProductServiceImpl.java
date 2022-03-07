package uz.pdp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Attachment;
import uz.pdp.entity.Category;
import uz.pdp.entity.Measurement;
import uz.pdp.entity.Product;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.helper.Utils;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.ProductAddDto;
import uz.pdp.model.ProductDto;
import uz.pdp.repository.ProductRepository;
import uz.pdp.service.AttachmentService;
import uz.pdp.service.CategoryService;
import uz.pdp.service.MeasurementService;
import uz.pdp.service.ProductService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.model.ApiResponse.response;

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
    public ResponseEntity<ApiResponse<ProductDto>> add(ProductAddDto addDto) {

        //Bunday nomli product oldin bazada mavjudmasligini tekshirish
        Optional<Product> productOptional = productRepository.findByName(addDto.getName());
        if (productOptional.isPresent()) {
            return response("This name product already exist", HttpStatus.FORBIDDEN);
        }

        ResponseEntity<ApiResponse<Category>> categoryResponse = categoryService.validate(addDto.getCategoryId());
        if (categoryResponse.getStatusCodeValue() != 200) {
            return response(categoryResponse.getBody().getErrorMessage(),categoryResponse.getStatusCode());
        }
        Category category = categoryResponse.getBody().getData();

        ResponseEntity<ApiResponse<Measurement>> measurementResponse = measurementService.validate(addDto.getMeasurementId());
        if (measurementResponse.getStatusCodeValue() != 200) {
            return response(measurementResponse.getBody().getErrorMessage(),measurementResponse.getStatusCode());
        }
        Measurement measurement = measurementResponse.getBody().getData();

        ResponseEntity<ApiResponse<Attachment>> attachmentResponse = attachmentService.validate(addDto.getAttachmentId());
        if (attachmentResponse.getStatusCodeValue() != 200) {
            return response(attachmentResponse.getBody().getErrorMessage(),attachmentResponse.getStatusCode());
        }
        Attachment attachment = attachmentResponse.getBody().getData();

        Product product = mapstructMapper.toProduct(addDto);
        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setCode(Utils.generateCode());
        product.setAttachment(attachment);
        product.setActive(true);

        Product savedProduct = productRepository.save(product);
        ProductDto productDto = mapstructMapper.toProductDto(savedProduct);
        return response(productDto,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        List<ProductDto> productDtoList = mapstructMapper.toProductDto(productList);
        return response(productDtoList);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductDto>> get(Long id) {

        ResponseEntity<ApiResponse<Product>> productResponse = validate(id);
        if (productResponse.getStatusCodeValue()!=200){
            return response(productResponse.getBody().getErrorMessage(), productResponse.getStatusCode());
        }
        Product product = productResponse.getBody().getData();
        ProductDto productDto = mapstructMapper.toProductDto(product);
        return response(productDto);
    }

    @Override
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllByCategory(Long categoryId,Pageable pageable) {
        ResponseEntity<ApiResponse<Category>> categoryResponse = categoryService.validate(categoryId);
        if (categoryResponse.getStatusCodeValue()!=200){
            return response(categoryResponse.getBody().getErrorMessage(), categoryResponse.getStatusCode());
        }
        Category category = categoryResponse.getBody().getData();
        Page<Product> productPage = productRepository.findAllByCategory(category,pageable);
        List<Product> productList = productPage.getContent();
        List<ProductDto> productDtoList = mapstructMapper.toProductDto(productList);
        return response(productDtoList,productPage.getTotalElements());
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> validate(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return response("Product id = " + id + ", not found!",HttpStatus.NOT_FOUND);
        }
        Product product = productOptional.get();
        if (!product.getActive()) {
            return response("Product id = " + id + ", is inactive!",HttpStatus.FORBIDDEN);
        }
        return response(product);
    }
}
