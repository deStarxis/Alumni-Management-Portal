package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Faculty extends Person{
    private String majorSubject;
    private Boolean isActive=false;

}
