package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Category;

@Getter
@Setter
public class CategoryDto extends AbstractDto{

    private Category parentCategory;
}
