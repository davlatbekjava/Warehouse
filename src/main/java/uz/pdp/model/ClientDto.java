package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientDto extends AbstractDto implements Serializable {
    private String phoneNumber;
}
