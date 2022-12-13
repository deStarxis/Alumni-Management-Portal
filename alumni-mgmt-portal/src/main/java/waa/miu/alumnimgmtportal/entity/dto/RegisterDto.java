package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Role;

import java.util.List;

@Data
public class RegisterDto {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String major;
    private int graduatedYear;
    private String majorSubject;
    private int universityId;
    private int gpa;
    private Role role;
    private List<Role> roles;
}
