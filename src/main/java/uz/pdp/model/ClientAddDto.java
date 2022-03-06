package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAddDto extends AbstractAddDto{
    private String phoneNumber;
}
