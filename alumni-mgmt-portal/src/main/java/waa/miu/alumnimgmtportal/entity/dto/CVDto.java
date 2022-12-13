package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;

@Data
public class CVDto {
    private int id;
    private StudentDto student;
    private String cvData;
    private boolean isDeleted = false;
}
