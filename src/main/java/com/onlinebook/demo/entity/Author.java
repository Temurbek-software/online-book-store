package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "author")
public class Author extends BaseEntity
{
    @Column(name = "firstName")
    @NotNull
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    private String lastName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "phoneNumber")
    @NotNull
    private String phoneNumber;

    @Column(name = "description")
    @NotNull
    private String description;

//    @JsonBackReference
    @ManyToMany(mappedBy = "productAuthor",
            cascade = {
                    CascadeType.PERSIST
            })
    @JsonIgnore
    private Set<Product> productDTOSet=new HashSet<>();

}
