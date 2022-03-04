package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "code",nullable = false)
    private String code;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "user_warehouse",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private Set<Warehouse> warehouses;
}
