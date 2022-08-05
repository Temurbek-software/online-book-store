package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "author")
public class Author extends BaseEntity {


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

    @ManyToMany(mappedBy = "productAuthors")
    @JsonIgnore
    private Set<Product> productSet = new HashSet<>();

    public Author(String firstName, String lastName,
                  String email, String phoneNumber,
                  String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

}
