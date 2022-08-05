package com.onlinebook.demo.service.company;

import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.repository.CompanyRepository;
import com.onlinebook.demo.service.roleservices.ConverterTo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private ConverterTo converterTo;

    @Override
    public ApiResult<List<CompanyDTO>> getAllCompanyInfo() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOS = companies.stream().map(s -> converterTo
                .COMPANY_DTO(s)).collect(Collectors.toList());
        return ApiResult.successResponse(companyDTOS);
    }

    @Override
    public ApiResult<CompanyDTO> getOneCompany(long id) {
        Company company = companyRepository.getById(id);
        return ApiResult.successResponse(converterTo.COMPANY_DTO(company));
    }

    @Override
    public ApiResult<?> deleteCompanyById(long id) {
        Company company = companyRepository.getById(id);
        company.setDeleted(true);
        companyRepository.save(company);
        return ApiResult.successResponse(converterTo.COMPANY_DTO(company));
    }

    @Override
    public ApiResult<?> saveCo0mpany(CompanyDTO companyDTO)
    {
        Company company=converterTo.toCompany(companyDTO);
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
