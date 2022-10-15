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

    private Set<ProductDTO> publisherSet;

}
