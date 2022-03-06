package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Output;
import uz.pdp.entity.Product;

import java.io.Serializable;

@Getter
@Setter
public class OutputProductDto implements Serializable {
    private Long id;

    private Double amount;

    private Double price;

    private Product product;

    private Output output;
}
