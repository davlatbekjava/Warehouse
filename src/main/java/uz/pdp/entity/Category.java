package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbsEntity{

    @ManyToOne
    @JoinColumn(name = "parent_category_id",referencedColumnName = "id")
    private Category parentCategory;
}
