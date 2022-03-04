package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Warehouse;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;

    private Boolean active = false;

    private Set<Warehouse> warehouses;


}
