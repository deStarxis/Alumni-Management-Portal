package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;

@Data
public class StudentDto {
    private int id;
    private String firstname;
    private String lastname;
    private String major;
    private int graduatedYear;
    private int universityId;
    private int gpa;
    private boolean isActive;
}
