package uz.pdp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "input")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date",columnDefinition = "default now()")
    private LocalDateTime date;

    @Column(name = "facture_number")
    private String factureNumber;

    @ManyToOne
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "supplier_id",referencedColumnName = "id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "currency_id",referencedColumnName = "id")
    private Currency currency;
}
