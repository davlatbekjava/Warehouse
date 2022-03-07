package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class InputDto implements Serializable {

    private Long id;

    private LocalDateTime date;

    private String factureNumber;

    private Warehouse warehouse;

    private Supplier supplier;

    private String currency;

}
