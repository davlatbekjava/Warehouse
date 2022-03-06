package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAddDto implements Serializable {

    @NotBlank(message = "Name cannot be null and blank!")
    private String name;

    @NotNull(message = "Active cannot be null!")
    private Boolean active;
}
