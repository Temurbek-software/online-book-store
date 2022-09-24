package com.onlinebook.demo.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Set<ProductDTO> productDTOSet;
    private String description;
    private boolean deleted;
    private Date createdAt;
    private Date updatedAt;

    public CategoryDTO(Long id, String name,
                       String description,
                       boolean deleted, Date createdAt,
                       Date updatedAt)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
