package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private boolean isDeleted=false;
}
