package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jobType;
    private String jobPosition;
    private String jobDescription;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    private Company company;
    private Date startDate;
    private Date endDate;
    private boolean isDeleted = false;
}
