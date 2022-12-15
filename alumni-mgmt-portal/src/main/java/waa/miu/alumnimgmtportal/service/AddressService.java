package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.dto.AddressDto;
import waa.miu.alumnimgmtportal.entity.dto.CompanyDto;

import java.util.List;

public interface AddressService {

    public List<AddressDto> findAll();


    public AddressDto findById(int id);

    public AddressDto  save(AddressDto addressDto );
    public void deleteById(int id);

    public AddressDto update(int id,AddressDto addressDto);
}
