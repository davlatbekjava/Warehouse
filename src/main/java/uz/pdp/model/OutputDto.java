package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.entity.Client;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class OutputDto implements Serializable {
    private Long id;

    private LocalDateTime date;

    private String factureNumber;

    private String code;

    private Warehouse warehouse;

    private Client client;

    private String currency;
}
