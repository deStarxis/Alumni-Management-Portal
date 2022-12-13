package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;
import waa.miu.alumnimgmtportal.entity.Address;

import javax.persistence.OneToOne;
@Data
public class CompanyDto {

    private int id;
    private String name;
    private AddressDto address;

}
