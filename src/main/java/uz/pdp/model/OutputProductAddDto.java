package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class OutputProductAddDto implements Serializable {

    private Double amount;

    private Double price;

    private Long productId;

    private Long outputId;
}
