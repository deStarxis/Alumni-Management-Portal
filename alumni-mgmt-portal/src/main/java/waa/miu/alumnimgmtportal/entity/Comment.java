package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Faculty faculty;
    private boolean isDeleted = false;
}
