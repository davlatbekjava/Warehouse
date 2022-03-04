package uz.pdp.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
public class Warehouse extends AbsEntity {

}
