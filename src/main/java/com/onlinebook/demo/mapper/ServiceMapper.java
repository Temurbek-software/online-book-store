package com.onlinebook.demo.mapper;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.payload.*;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    Category mapToCategory(CategoryDTO categoryDTO);
    @Mapping(target = "productDTOSet",ignore = true)
    AuthorDTO mapToAuthorDTO(Author author);

    @Mapping(target = "productDTOSet", ignore = true)
    Author mapToAuthor(AuthorDTO authorDTO);

    @Mapping(target = "categoryProduct",ignore = true)
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(target = "publisherSet", ignore = true)
    PublisherDTO mapToPublisherDTO(Publisher publisher);

    @Mapping(target = "publisherProduct", ignore = true)
    Publisher mapToPublisherDTO(PublisherDTO publisherDTO);

    @Mapping(target = "companyProduct",ignore = true)
    CompanyDTO mapToCompanyDTO(Company company);

    @Mapping(target = "companyProduct",ignore = true)
    @Mapping(target = "id",ignore = true)
    Company mapToCompany(CompanyDTO companyDTO);

    @Mapping(target = "deleted",ignore = true)//deleted, createdAt, updatedAt,
    @Mapping(target = "createdAt",ignore = true)//deleted, createdAt, updatedAt,
    @Mapping(target = "updatedAt",ignore = true)//deleted, createdAt, updatedAt,
    @Mapping(target = "id",ignore = true)//deleted, createdAt, updatedAt,
    Product mapToProduct(ProductDTO productDTO);
    Author mapToAuthorDTO(AuthorDTO authorDTO);

}
