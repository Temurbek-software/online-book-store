package com.onlinebook.demo.mapper;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.payload.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "productDTOSet", ignore = true)
    AuthorDTO mapToAuthorDTO(Author author);

    @Mapping(target = "authorProduct", ignore = true)
    Author mapToAuthor(AuthorDTO authorDTO);

    @Mapping(target = "categoryProductDTOSet", ignore = true)
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(target = "publisherSet", ignore = true)
    PublisherDTO mapToPublisherDTO(Publisher publisher);

    @Mapping(target = "publisherProduct", ignore = true)
    Publisher mapToPublisherDTO(PublisherDTO publisherDTO);

    @Mapping(target = "companyProductDTOSet", ignore = true)
    CompanyDTO mapToCompanyDTO(Company company);

    Product mapToProduct(ProductDTO productDTO);

}
