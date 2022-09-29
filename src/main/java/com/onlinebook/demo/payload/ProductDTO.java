package com.onlinebook.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.*;
import lombok.*;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;

    private String bookName;

    private Double e_price;

    private Double printed_Price;

    private Double audio_price;

    private Date yearOfPublished;

    private Integer pageNumb;

    private String description;

    private String language;

    private String isbnNumber;

    private boolean deleted ;
    private Date createdAt;
    private Date updatedAt;
    private Set<AuthorDTO> authors;
    private CategoryDTO category;
    private PublisherDTO publisher;
    private CompanyDTO company;

    public ProductDTO(Long id, String bookName, Double e_price,
                      Double printed_Price, Double audio_price,
                      Date yearOfPublished, Integer pageNumb,
                      String description,
                      String language, String isbnNumber,
                      boolean deleted, Date createdAt,
                      Date updatedAt) {
        this.id = id;
        this.bookName = bookName;
        this.e_price = e_price;
        this.printed_Price = printed_Price;
        this.audio_price = audio_price;
        this.yearOfPublished = yearOfPublished;
        this.pageNumb = pageNumb;
        this.description = description;
        this.language = language;
        this.isbnNumber = isbnNumber;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductDTO(Product product)
    {
        this.id = product.getId();
        this.bookName = product.getBookName();
        this.e_price = product.getE_price();
        this.printed_Price = product.getPrinted_Price();
        this.audio_price = product.getAudio_price();
        this.yearOfPublished = product.getYearOfPublished();
        this.pageNumb = product.getPageNumb();
        this.description = product.getDescription();
        this.language = product.getLanguage();
        this.isbnNumber = product.getIsbnNumber();
        this.deleted = product.isDeleted();
        this.createdAt = product.getCreatedAt();
        this.category=new CategoryDTO(
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                product.getCategory().isDeleted(),
                product.getCategory().getCreatedAt(),
                product.getCategory().getUpdatedAt()
        );
        this.publisher = new PublisherDTO(
                product.getPublisher().getId(),
                product.getPublisher().getName(),
                product.getPublisher().getAddress(),
                product.getPublisher().getPhoneNumber(),
                product.getPublisher().getEmail(),
                product.getPublisher().getEstablished_year(),
                product.getPublisher().getDescription(),
                product.getPublisher().isDeleted(),
                product.getPublisher().getCreatedAt(),
                product.getPublisher().getUpdatedAt()
        );
        this.company = new CompanyDTO(
                product.getCompany().getId(),
                product.getCompany().getNameOfCompany(),
                product.getCompany().getAddress(),
                product.getCompany().getPhoneNumber(),
                product.getCompany().getEmail(),
                product.getCompany().getYearOfPublished(),
                product.getCompany().getDescription(),
                product.getCompany().isDeleted(),
                product.getCompany().getCreatedAt(),
                product.getCompany().getUpdatedAt()
        );
        this.authors=getAllAuthors(product.getProductAuthors());

    }
    public Set<AuthorDTO> getAllAuthors(Set<Author> authors)
    {
        Set<AuthorDTO> authorDTOS= new HashSet<>();
        for (Author author:authors)
        {
            AuthorDTO authorDTO= new AuthorDTO(
                    author.getId(),
                    author.getFirstName(),
                    author.getEmail(),
                    author.getDescription(),
                    author.getPhoneNumber(),
                    author.getLastName(),
                    author.isDeleted(),
                    author.getCreatedAt(),
                    author.getUpdatedAt()
            );
            authorDTOS.add(authorDTO);
        }
        return authorDTOS;
    }
}
