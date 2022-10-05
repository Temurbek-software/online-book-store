package com.onlinebook.demo.payload;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Product;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String description;

    //    @JsonIgnore
    private Set<ProductDTO> productDTOSet;


    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.firstName = author.getFirstName();
        this.phoneNumber = author.getPhoneNumber();
        this.email = author.getEmail();
        this.description = author.getDescription();
        this.productDTOSet = getProductDTO(author.getAuthorProduct());
    }

    public Set<ProductDTO> getProductDTO(Set<Product> products) {
        Set<ProductDTO> productDTOSet = new HashSet<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getBookName(),
                    product.getE_price(),
                    product.getPrinted_Price(),
                    product.getAudio_price(),
                    product.getYearOfPublished(),
                    product.getPageNumb(),
                    product.getDescription(),
                    product.getLanguage(),
                    product.getIsbnNumber()
            );
            productDTOSet.add(productDTO);
        }
        return productDTOSet;
    }

    public AuthorDTO(Long id, String firstName,
                     String lastName, String email,
                     String phoneNumber, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }
}
