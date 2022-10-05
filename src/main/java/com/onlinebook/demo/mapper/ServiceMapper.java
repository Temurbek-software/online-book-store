package com.onlinebook.demo.mapper;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.payload.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(target = "productDTOSet", ignore = true)
    AuthorDTO mapToAuthorDTO(Author author);

    @Mapping(target = "categoryProductDTOSet", ignore = true)
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(target = "publisherProductDTOSet", ignore = true)
    PublisherDTO mapToPublisherDTO(Publisher publisher);

    @Mapping(target = "companyProductDTOSet", ignore = true)
    CompanyDTO mapToCompanyDTO(Company company);

//    @Mapping(target = "",ignore = true)
//    @Mapping(target = "")
    Product mapToProduct(ProductDTO productDTO);

}
