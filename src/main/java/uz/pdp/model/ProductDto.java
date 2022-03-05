package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Attachment;
import uz.pdp.entity.Category;
import uz.pdp.entity.Measurement;

@Getter
@Setter
public class ProductDto extends AbstractDto {

    private String code;

    private Category category;

    private Attachment attachment;

    private Measurement measurement;

}
