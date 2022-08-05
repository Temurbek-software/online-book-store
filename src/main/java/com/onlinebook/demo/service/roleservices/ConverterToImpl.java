package com.onlinebook.demo.service.roleservices;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.entity.Publisher;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.payload.PublisherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterToImpl implements ConverterTo {

    @Override
    public ProductDTO toProductDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getBookName(),
                product.getE_price(),
                product.getAudio_price(),
                product.getPrinted_Price(),
                product.getYearOfPublished(),
                product.getPageNumb(),
                product.getLanguage(),
                product.getDescription(),
                product.getIsbnNumber(),
                product.getProductAuthors(),
                product.getPublisher());
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getBookName(),
                productDTO.getE_price(),
                productDTO.getPrinted_Price(),
                productDTO.getAudio_price(),
                productDTO.getYearOfPublished(),
                productDTO.getPageNumb(),
                productDTO.getLanguage(),
                productDTO.getDescription(),
                productDTO.getIsbnNumber());
    }

    @Override
    public AuthorDTO toAuhtorDTO(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getEmail(),
                author.getPhoneNumber(),
                author.getDescription(),
                author.getProductSet()
        );
    }

    @Override
    public Author toAuhtor(AuthorDTO authorDTO) {
        return new Author(
                authorDTO.getLastName(),
                authorDTO.getFirstName(),
                authorDTO.getEmail(),
                authorDTO.getDescription(),
                authorDTO.getPhoneNumber()
        );
    }

    @Override
    public Publisher toPublisher(PublisherDTO publisherDTO) {
        return new Publisher(
                publisherDTO.getName(),
                publisherDTO.getAddress(),
                publisherDTO.getPhoneNumber(),
                publisherDTO.getEmail(),
                publisherDTO.getEstablished_year(),
                publisherDTO.getDescription()
        );
    }

    @Override
    public PublisherDTO toPublisherDTO(Publisher publisher) {
        return new PublisherDTO(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getPhoneNumber(),
                publisher.getEmail(),
                publisher.getEstablished_year(),
                publisher.getDescription(),
                publisher.getProductSet()
        );
    }

    @Override
    public Company toCompany(CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getNameOfCompany(),
                companyDTO.getAddress(),
                companyDTO.getPhoneNumber(),
                companyDTO.getEmail(),
                companyDTO.getYearOfPublished(),
                companyDTO.getDescription(),
                companyDTO.getProductSet()
        );
    }

    @Override
    public CompanyDTO COMPANY_DTO(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getNameOfCompany(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getYearOfPublished(),
                company.getDescription(),
                company.getProduct_Id()
        );
    }
}
