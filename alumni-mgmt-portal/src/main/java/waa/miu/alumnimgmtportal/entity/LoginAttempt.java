package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LoginAttempt {
    @Id
    private int id;
    private int personId;
    private int count;
    private Date dateTime;
}
