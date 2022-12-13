package waa.miu.alumnimgmtportal.model;

import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Address;
@Data
public class JobCount {

    Long count;
    String address;
    public JobCount(Long count,String city, String state){
        this.count=count;
        this.address= city + ", "+ state;
    }

}
