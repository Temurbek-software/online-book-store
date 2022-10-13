package com.onlinebook.demo.controller;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController
{
    private final CompanyService companyService;

    @GetMapping("/all")
    public ApiResult<?> getAllCompany()
    {
        return companyService.getAllCompanyInfo();
    }

    @PostMapping("/insert")
    public ApiResult<String> saveCompany(CompanyDTO companyDTO) {
        return (ApiResult<String>) companyService.saveCompany(companyDTO);
    }

    @GetMapping("/{id}")
    public ApiResult<CompanyDTO> getOneCompany(@PathVariable long id) {
        return companyService.getOneCompany(id);
    }
    @PutMapping("/update/{id}")
    public ApiResult<?> updateCompany(@PathVariable long id,
                                      @RequestBody CompanyDTO companyDTO)
    {
       return  companyService.changeCompany(companyDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deleteCompanyWithId(@PathVariable Long id)
    {
        return companyService.deleteCompanyById(id);
    }
}
