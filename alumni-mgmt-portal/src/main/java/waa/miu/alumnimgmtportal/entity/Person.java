package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean isActive = true;
    @OneToOne
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles;
}
