package waa.miu.alumnimgmtportal.service;

import waa.miu.alumnimgmtportal.entity.Company;
import waa.miu.alumnimgmtportal.entity.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    public List<CompanyDto> findAll();


    public CompanyDto findById(int id);

    public CompanyDto  save(CompanyDto company );
    public void deleteById(int id);

    public CompanyDto update(int id,CompanyDto companyDto);

}
