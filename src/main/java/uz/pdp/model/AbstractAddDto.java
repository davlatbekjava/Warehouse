package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAddDto implements Serializable {

    private String name;

    private Boolean active;
}
