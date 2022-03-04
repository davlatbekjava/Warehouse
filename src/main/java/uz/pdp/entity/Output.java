package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "output")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date",columnDefinition = "timestamp default now()")
    private LocalDateTime date;

    @Column(name = "facture_number")
    private String facture_number;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "currency_id",referencedColumnName = "id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Client client;


}
