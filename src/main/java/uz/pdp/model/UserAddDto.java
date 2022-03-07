package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserAddDto implements Serializable {

    @NotBlank(message = "FirstName cannot be Blank and Null!")
    private String firstName;

    @NotBlank(message = "LastName cannot be Blank and Null!")
    private String lastName;

    @NotBlank(message = "Phone number cannot be Blank and Null!")
    private String phoneNumber;

    @NotBlank(message = "FirstName cannot be Blank and Null!")
    private String password;

    @NotNull(message = "Warehouse id cannot be Blank and Null!")
    private List<Long> warehouseIds;

    private Boolean active = false;
}
