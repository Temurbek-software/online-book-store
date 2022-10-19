package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "book_name")
    @NotNull
    private String bookName;

    @Column(name = "electronic_price")
    @NotNull
    private Double e_price;

    @Column(name = "printed_price")
    @NotNull
    private Double printed_Price;

    @Column(name = "audio_price")
    @NotNull
    private Double audio_price;

    @Column(name = "yearOfPublished")
    @NotNull
    private Date yearOfPublished;

    @Column(name = "pageNumber")
    @NotNull
    private Integer pageNumb;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "Language")
    @NotNull
    private String language;

    @Column(name = "ISBNnumber")
    @NotNull
    private String isbnNumber;

    @ManyToOne(cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "company_Id", referencedColumnName = "id")
    private Company productCompany;

    @ManyToOne(cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_Id", referencedColumnName = "id")
    private Publisher productPublisher;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(
            name = "product_author",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonIgnore
    private Set<Author> productAuthor = new HashSet<>();

    public Product(String bookName,
                   Double e_price,
                   Double printed_Price,
                   Double audio_price,
                   Date yearOfPublished,
                   Integer pageNumb,
                   String description,
                   String language,
                   String isbnNumber,
                   Category category,
                   Company company,
                   Set<Author> productAuthors,
                   Publisher publisher)
    {
        this.bookName = bookName;
        this.e_price = e_price;
        this.printed_Price = printed_Price;
        this.audio_price = audio_price;
        this.yearOfPublished = yearOfPublished;
        this.pageNumb = pageNumb;
        this.description = description;
        this.language = language;
        this.isbnNumber = isbnNumber;
        this.category = category;
        this.productCompany = company;
        this.productAuthor = productAuthors;
        this.productPublisher = publisher;
    }
}
