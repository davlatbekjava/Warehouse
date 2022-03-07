package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ProductAddDto implements Serializable {

    @NotBlank(message = "Name cannot be null!")
    private String name;

    @NotNull(message = "Category id cannot be null!")
    private Long categoryId;

    @NotNull(message = "Measurement id cannot be null!")
    private Long measurementId;

    @NotNull(message = "Attachment id cannot be null!")
    private Long attachmentId;
}
