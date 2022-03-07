package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClientAddDto extends AbstractAddDto{

    @NotBlank(message = "Phone number cannot be null and blank!")
    private String phoneNumber;
}
