package waa.miu.alumnimgmtportal.entity.dto;
import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Job;
import waa.miu.alumnimgmtportal.entity.JobApplicationId;
import waa.miu.alumnimgmtportal.entity.Student;

import java.time.LocalDateTime;

@Data
public class JobApplicationDto {
    private JobApplicationId id;
    private JobDto job;
    private StudentDto student;
    private LocalDateTime appliedDate;
}
