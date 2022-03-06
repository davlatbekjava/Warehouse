package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Input;
import uz.pdp.entity.Product;

import java.time.LocalDate;

@Getter
@Setter
public class InputProductDto {

    private Long id;

    private Double amount;

    private Double price;

    private LocalDate expireDate;

    private Product product;

    private Input input;
}
