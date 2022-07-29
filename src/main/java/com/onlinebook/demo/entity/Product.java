package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product extends BaseEntity {
    //    @NotBlank(message = "Name is mandatory")
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "electronic_price")
    private Double e_price;

    @Column(name = "printed_price")
    private Double printed_Price;

    @Column(name = "audio_price")
    private Double audio_price;

    @Column(name = "yearOfPublished")
    private Date yearOfPublished;

    @Column(name = "pageNumber")
    private Integer pageNumb;

    @Column(name = "description")
    private String description;

    @Column(name = "Language")
    private String language;

    @Column(name = "ISBNnumber")
    private String isbnNumber;


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_author",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonIgnore
    private Set<Author> productAuthors = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    @JsonIgnore
    private Publisher publisher;

    public Product(String bookName,
                   Double e_price,
                   Double printed_Price,
                   Double audio_price,
                   Date yearOfPublished,
                   Integer pageNumb,
                   String description,
                   String language,
                   String isbnNumber) {
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
