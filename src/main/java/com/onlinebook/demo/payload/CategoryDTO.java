package com.onlinebook.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlinebook.demo.entity.Category;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;

    private Set<ProductDTO> categoryProductDTOSet;
    private String description;

    public CategoryDTO(Long id, String name,
                       String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
