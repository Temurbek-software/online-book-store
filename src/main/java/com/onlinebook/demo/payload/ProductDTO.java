package com.onlinebook.demo.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable {
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

    private Set<Author> authors;

    private Publisher publisher;
}
