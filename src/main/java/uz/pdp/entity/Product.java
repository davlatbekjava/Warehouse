package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbsEntity{

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "attachmnet_id",referencedColumnName = "id")
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name = "measurement_id",referencedColumnName = "id")
    private Measurement measurement ;
}
