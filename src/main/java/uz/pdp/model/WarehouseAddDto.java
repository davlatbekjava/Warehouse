package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WarehouseAddDto implements Serializable {

    private String name;

    private Boolean active;
}
