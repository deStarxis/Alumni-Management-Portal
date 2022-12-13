package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Student student;
    private String cvData;
    private boolean isDeleted = false;
}
