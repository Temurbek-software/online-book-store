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

    private Set<AuthorDTO> authorsList;
    private CategoryDTO category_Id;
    private PublisherDTO publisher_Id;
    private CompanyDTO company_Id;

    public ProductDTO(Long id, String bookName, Double e_price,
                      Double printed_Price, Double audio_price,
                      Date yearOfPublished, Integer pageNumb,
                      String description,
                      String language, String isbnNumber) {
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
    }

}
