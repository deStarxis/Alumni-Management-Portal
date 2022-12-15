package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Company;
import waa.miu.alumnimgmtportal.entity.dto.CompanyDto;
import waa.miu.alumnimgmtportal.repository.CompanyRepo;
import waa.miu.alumnimgmtportal.service.CompanyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private  final CompanyRepo companyRepo;
    private  final ModelMapper modelMapper;
    @Override
    public List<CompanyDto> findAll() {
        var companyList = companyRepo.findAll().stream().filter(c->!c.isDeleted()).map(p -> modelMapper.map(p, CompanyDto.class)).toList();
        return companyList;


    }

    @Override
    public CompanyDto findById(int id) {
        var company=companyRepo.findById(id).filter(c->!c.isDeleted()).orElseThrow(()->new RuntimeException("Company not found"));;
        return modelMapper.map(company,CompanyDto.class);
    }


    @Override
    public CompanyDto save(CompanyDto companyDto) {
        Company company=modelMapper.map(companyDto,Company.class);
        companyRepo.save(company);
        return modelMapper.map(company,CompanyDto.class);

    }

    @Override
    public void deleteById(int id) {
        Company company = companyRepo.findById(id).orElseThrow(()->new RuntimeException("Company not found"));
        company.setId(id);
        company.setDeleted(true);
        companyRepo.save(company);
    }

    @Override
    public CompanyDto update(int id,CompanyDto companyDto) {
        companyRepo.findById(id).orElseThrow(()->new RuntimeException("Company not found"));
        Company company=modelMapper.map(companyDto,Company.class);
        company.setId(id);
        companyRepo.save(company);
        return modelMapper.map(company,CompanyDto.class);
    }


}
