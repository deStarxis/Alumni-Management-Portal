package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String role;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
