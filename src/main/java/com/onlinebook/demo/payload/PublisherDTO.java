package com.onlinebook.demo.payload;

import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO implements Serializable {
    private Long id;

    private String name;

    private String address;

    private Integer phoneNumber;

    private String email;

    private Date established_year;

    private String description;

    private Set<ProductDTO> publisherProductDTOSet;

    public PublisherDTO(Publisher publisher) {
        AuthorDTO authorDTO=new AuthorDTO();
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.address = publisher.getAddress();
        this.phoneNumber = publisher.getPhoneNumber();
        this.email = publisher.getEmail();
        this.established_year = publisher.getEstablished_year();
        this.description = publisher.getDescription();
        this.publisherProductDTOSet=authorDTO.getProductDTO(publisher.getPublisherProduct());
    }

    public PublisherDTO(Long id, String name,
                        String address,
                        Integer phoneNumber,
                        String email, Date established_year,
                        String description, boolean deleted,
                        Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.established_year = established_year;
        this.description = description;
    }
}
