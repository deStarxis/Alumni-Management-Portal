package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JobExperienceDto {
    private int id;
    private String jobType;
    private String jobPosition;
    private String jobDescription;
    private StudentDto student;
    private String position;
    private CompanyDto company;
    private Date startDate;
    private Date endDate;
}
