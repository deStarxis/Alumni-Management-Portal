package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.Company;
import waa.miu.alumnimgmtportal.entity.dto.CompanyDto;
import waa.miu.alumnimgmtportal.service.CompanyService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping
    public List<CompanyDto> findAll(){
       return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDto findById(@PathVariable int id){
        return companyService.findById(id);
    }

    @PostMapping
    public CompanyDto save(@RequestBody CompanyDto company){
      return companyService.save(company);


    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable int id){
        companyService.deleteById(id);
    }


    @PutMapping("/{id}")
    public CompanyDto update(@PathVariable int id, @RequestBody CompanyDto companyDto){
       return companyService.update(id,companyDto);

    }




}
