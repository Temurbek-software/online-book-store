package com.onlinebook.demo.mapper;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.payload.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    Category mapToCetegory(CategoryDTO categoryDTO);
    AuthorDTO mapToAuthorDTO(Author author);

    @Mapping(target = "productDTOSet", ignore = true)
    Author mapToAuthor(AuthorDTO authorDTO);

    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(target = "publisherSet", ignore = true)
    PublisherDTO mapToPublisherDTO(Publisher publisher);

    @Mapping(target = "publisherProduct", ignore = true)
    Publisher mapToPublisherDTO(PublisherDTO publisherDTO);

    CompanyDTO mapToCompanyDTO(Company company);

    @Mapping(target = "companyProduct",ignore = true)
    @Mapping(target = "id",ignore = true)
    Company mapToCompany(CompanyDTO companyDTO);

    @Mapping(target = "deleted",ignore = true)//deleted, createdAt, updatedAt,
    @Mapping(target = "createdAt",ignore = true)//deleted, createdAt, updatedAt,
    @Mapping(target = "updatedAt",ignore = true)//deleted, createdAt, updatedAt,
    Product mapToProduct(ProductDTO productDTO);
    Author mapToAuthorDTO(AuthorDTO authorDTO);

}
