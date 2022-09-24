package com.onlinebook.demo.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDTO {
    private Long id;
    private String nameOfCompany;
    private String address;
    private String phoneNumber;
    private String email;
    private Date yearOfPublished;
    private String description;
    private Set<ProductDTO> productSet;
    private boolean deleted;
    private Date createdAt;
    private Date updatedAt;

    public CompanyDTO(Company company) {
        AuthorDTO authorDTO= new AuthorDTO();
        this.id = company.getId();
        this.nameOfCompany = company.getNameOfCompany();
        this.address = company.getAddress();
        this.phoneNumber = company.getPhoneNumber();
        this.email = company.getEmail();
        this.yearOfPublished = company.getYearOfPublished();
        this.description = company.getDescription();
        this.productSet = authorDTO.getProductDTO(company.getProduct_Id());
        this.deleted = company.isDeleted();
        this.createdAt = company.getCreatedAt();
        this.updatedAt = company.getUpdatedAt();
    }

    public CompanyDTO(Long id, String nameOfCompany,
                      String address,
                      String phoneNumber,
                      String email, Date yearOfPublished,
                      String description, boolean deleted,
                      Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.yearOfPublished = yearOfPublished;
        this.description = description;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
