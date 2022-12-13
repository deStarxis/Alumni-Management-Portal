package waa.miu.alumnimgmtportal.entity.dto;

import lombok.Data;

@Data
public class AddressDto {
    private int id;
    private String city;
    private String state;
    private int zip;
    private String street;
}
