package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "input_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "price")
    private Double price;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "input_id",referencedColumnName = "id")
    private Input input;


}
