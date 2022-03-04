package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto implements Serializable {

    private Long id;

    private String name;

    private Boolean active;
}
