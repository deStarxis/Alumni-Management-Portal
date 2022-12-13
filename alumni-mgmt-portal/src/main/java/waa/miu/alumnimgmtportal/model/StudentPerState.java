package waa.miu.alumnimgmtportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.dto.StudentDto;

@Data

public class StudentPerState {
    private  Long count;
    private String state;
    public StudentPerState(Long count, String state) {
        this.count = count;
        this.state = state;
    }

}
