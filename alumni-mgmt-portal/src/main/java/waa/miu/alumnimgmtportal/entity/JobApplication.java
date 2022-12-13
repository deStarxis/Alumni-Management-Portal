package waa.miu.alumnimgmtportal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDateTime;

@Entity
@Data
public class JobApplication {
    @EmbeddedId
    private JobApplicationId id = new JobApplicationId();

    @ManyToOne
    @MapsId("jobId")
    @JsonManagedReference(value = "job-jobApplication")
    private Job job;

    @ManyToOne
    @MapsId("studentId")
    @JsonManagedReference(value = "student-jobApplication")
    private Student student;

    private LocalDateTime appliedDate = LocalDateTime.now();
}
