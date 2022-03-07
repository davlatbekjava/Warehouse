package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class InputProductAddDto {

    @NotNull(message = "Amount cannot be null!")
    private Double amount;

    @NotNull(message = "Price cannot be null!")
    private Double price;

    @NotNull(message = "Expire date cannot be null!")
    private LocalDate expireDate;

    @NotNull(message = "Product id cannot be null!")
    private Long productId;

    @NotNull(message = "Input id cannot be null!")
    private Long inputId;
}
