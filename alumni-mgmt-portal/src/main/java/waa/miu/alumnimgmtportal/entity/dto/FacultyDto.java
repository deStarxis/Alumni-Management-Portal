package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;

@Data
public class FacultyDto {
    private int id;
    private String firstname;
    private String lastname;
    private String majorSubject;
    private boolean isActive;
}
