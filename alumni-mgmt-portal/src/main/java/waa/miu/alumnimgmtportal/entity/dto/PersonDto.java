package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Role;

import java.util.List;

@Data
public class PersonDto {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String major;
    private String majorSomething;
    private int graduatedYear;
    private String majorSubject;
    private int universityId;
    private int gpa;
    private AddressDto address;
    private List<RoleDto> roles;
}
