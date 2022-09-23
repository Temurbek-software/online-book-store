package com.onlinebook.demo.service.company;

import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepository;

    @Override
    public ApiResult<List<CompanyDTO>> getAllCompanyInfo() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOS = companies.stream().map(CompanyDTO::new)
                .collect(Collectors.toList());
        return ApiResult.successResponse(companyDTOS);
    }

    @Override
    public ApiResult<CompanyDTO> getOneCompany(long id) {
        Company company = companyRepository.getById(id);
        CompanyDTO companyDTO=new CompanyDTO(company);
        return ApiResult.successResponse(companyDTO);
    }

    @Override
    public ApiResult<?> deleteCompanyById(long id) {
        Company company = companyRepository.getById(id);
        company.setDeleted(true);
        companyRepository.save(company);
        CompanyDTO companyDTO=new CompanyDTO(company);
        return ApiResult.successResponse(companyDTO);
    }

    @Override
    public ApiResult<?> saveCompany(CompanyDTO companyDTO)
    {

        Company company=new Company(companyDTO);
        companyRepository.save(company);
        return ApiResult.successResponse(companyDTO);
    }
    @Override
    public ApiResult<?> changeCompany(CompanyDTO companyDTO,long id)
    {
        Company company=companyRepository.getById(id);
        company.setEmail(companyDTO.getEmail());
        company.setNameOfCompany(companyDTO.getNameOfCompany());
        company.setAddress(companyDTO.getAddress());
        company.setPhoneNumber(companyDTO.getPhoneNumber());
        company.setYearOfPublished(companyDTO.getYearOfPublished());
        company.setDescription(companyDTO.getDescription());
        return ApiResult.successResponse(company,true);
    }



}
