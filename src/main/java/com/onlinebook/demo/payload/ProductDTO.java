package com.onlinebook.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Company;
import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.entity.Publisher;
import lombok.*;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private boolean deleted;
    private Date createdAt;

    private Set<AuthorDTO> authors;

    private PublisherDTO publisher;
    private CompanyDTO company;

    public ProductDTO(Product product) {
        this.id=product.getId();
        this.bookName = product.getBookName();
        this.e_price = product.getE_price();
        this.printed_Price = product.getPrinted_Price();
        this.audio_price = product.getAudio_price();
        this.yearOfPublished = product.getYearOfPublished();
        this.pageNumb = product.getPageNumb();
        this.description = product.getDescription();
        this.language = product.getLanguage();
        this.isbnNumber = product.getIsbnNumber();
        this.deleted=product.isDeleted();
        this.createdAt=product.getCreatedAt();
    }
}
