package com.onlinebook.demo.service.company;

import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.exception.RestException;
import com.onlinebook.demo.mapper.ServiceMapper;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.repository.CompanyRepository;
import com.onlinebook.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ApiResult<List<CompanyDTO>> getAllCompanyInfo() {
        List<Company> companies = companyRepository.getAllCompanyIfNotDeleted(false);
        List<CompanyDTO> companyDTOS = companies
                .stream().map(serviceMapper::mapToCompanyDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(companyDTOS);
    }

    @Override
    public ApiResult<CompanyDTO> getOneCompany(long id) {
        Company company = companyRepository.getById(id);
        CompanyDTO companyDTO = serviceMapper.mapToCompanyDTO(company);
        return ApiResult.successResponse(companyDTO);
    }

    @Override
    public ApiResult<?> deleteCompanyById(long id) {
        Company company = companyRepository.getById(id);
        company.setDeleted(true);
        companyRepository.save(company);
        CompanyDTO companyDTO = serviceMapper.mapToCompanyDTO(company);
        return ApiResult.successResponse(companyDTO);
    }

    @Override
    public ApiResult<?> saveCompany(CompanyDTO companyDTO) {
        Company company = serviceMapper.mapToCompany(companyDTO);
        companyRepository.save(company);
        return ApiResult.successResponse(companyDTO);
    }

    @Override
    public ApiResult<?> changeCompany(CompanyDTO companyDTO, long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company company1 = companyRepository.getById(id);
            company1.setEmail(companyDTO.getEmail());
            company1.setNameOfCompany(companyDTO.getNameOfCompany());
            company1.setAddress(companyDTO.getAddress());
            company1.setPhoneNumber(companyDTO.getPhoneNumber());
            company1.setYearOfPublished(companyDTO.getYearOfPublished());
            company1.setDescription(companyDTO.getDescription());
        } else {
            throw RestException.restThrow("not found", "company",
                    id, MessageService.getMessage("NOT_FOUND_COMPANY"));
        }

        return ApiResult.successResponse(company, "Company has changed");
    }


}
