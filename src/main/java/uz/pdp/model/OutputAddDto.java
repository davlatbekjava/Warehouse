package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OutputAddDto implements Serializable{

    private String factureNumber;

    private Long warehouseId;

    private Long currencyId;

    private Long clientId;
}
