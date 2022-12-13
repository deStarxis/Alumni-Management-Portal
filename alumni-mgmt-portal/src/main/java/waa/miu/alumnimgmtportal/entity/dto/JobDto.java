package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Tag;

import java.util.List;

@Data
public class JobDto {
    private int id;
    private String jobTitle;
    private String jobType;
    private String description;
    private AddressDto address;
    private StudentDto student;
    private CompanyDto company;
    private List<Tag> tags;
}
