package com.onlinebook.demo.service.company;

import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CompanyDTO;

import java.util.List;

public interface CompanyService {
    ApiResult<List<CompanyDTO>> getAllCompanyInfo();
    ApiResult<CompanyDTO> getOneCompany(long id);
    ApiResult<?> deleteCompanyById(long id);
    ApiResult<?> saveCompany(CompanyDTO companyDTO);
    ApiResult<?> changeCompany(CompanyDTO companyDTO,long id);

    Company mapToCompany(CompanyDTO companyDTO);

}
