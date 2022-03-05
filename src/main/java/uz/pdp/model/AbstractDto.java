package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class AbstractDto implements Serializable{
    private Long id;

    private String name;

    private Boolean active;
}
