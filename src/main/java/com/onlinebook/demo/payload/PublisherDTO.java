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

    private boolean deleted = false;
    private Date createdAt;
    private Date updatedAt;
    private Set<ProductDTO> productSet;

    public PublisherDTO(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.address = publisher.getAddress();
        this.phoneNumber = publisher.getPhoneNumber();
        this.email = publisher.getEmail();
        this.established_year = publisher.getEstablished_year();
        this.description = publisher.getDescription();
        this.createdAt=publisher.getCreatedAt();
        this.updatedAt=publisher.getUpdatedAt();
        this.productSet=publisher.getProductSet()
                .stream().map(ProductDTO::new).collect(Collectors.toSet());
    }
}
