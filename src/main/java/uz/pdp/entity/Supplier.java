package uz.pdp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends AbsEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

}
