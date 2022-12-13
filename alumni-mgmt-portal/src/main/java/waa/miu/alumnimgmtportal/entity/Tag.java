package waa.miu.alumnimgmtportal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String tag;

//    @ManyToMany(mappedBy = "tags")
//    @JsonBackReference
//    private List<Job> jobs;
}
