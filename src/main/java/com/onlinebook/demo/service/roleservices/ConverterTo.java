package com.onlinebook.demo.service.roleservices;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.entity.Publisher;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.payload.PublisherDTO;

public interface ConverterTo {
    ProductDTO toProductDto(Product product);
    Product toProduct(ProductDTO productDTO);
    AuthorDTO toAuhtorDTO(Author author);
    Author toAuhtor(AuthorDTO authorDTO);
    Publisher toPublisher(PublisherDTO publisherDTO);
    PublisherDTO toPublisherDTO(Publisher publisher);
    Company toCompany(CompanyDTO companyDTO);
    CompanyDTO COMPANY_DTO(Company company);
}
