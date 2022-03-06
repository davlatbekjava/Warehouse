package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class InputAddDto implements Serializable {

    private String factureNumber;

    private Long warehouseId;

    private Long supplierId;

    private Long currencyId;
}
