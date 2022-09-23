package com.onlinebook.demo.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Product;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO
{
    private  Long id;

    private  String firstName;

    private  String lastName;

    private  String email;

    private  String phoneNumber;

    private  String description;

    private  Set<ProductDTO> productSet=new HashSet<>();
}
