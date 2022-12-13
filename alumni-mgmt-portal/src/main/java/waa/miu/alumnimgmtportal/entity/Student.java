package waa.miu.alumnimgmtportal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Student extends Person{
    private String major;
    private int graduatedYear;
    private int universityId;
    private double gpa;

    @OneToMany(mappedBy = "student")
    @JsonBackReference(value = "student-jobApplication")
    private List<JobApplication> jobApplications;
}
