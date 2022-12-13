package waa.miu.alumnimgmtportal.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JobApplicationId implements Serializable {
    private int jobId;
    private int studentId;
}
