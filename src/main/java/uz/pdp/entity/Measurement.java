package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "measurement")
@Getter
@Setter
@NoArgsConstructor
public class Measurement extends AbsEntity{


}
