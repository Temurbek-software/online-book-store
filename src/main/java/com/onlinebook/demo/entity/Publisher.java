package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "publisher")
public class Publisher extends BaseEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "adress")
    @NotNull
    private String address;

    @Column(name = "phoneNumber")
    @NotNull
    private Integer phoneNumber;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "estabilishedYear")
    @NotNull
    private Date established_year;

    @Column(name = "description")
    @NotNull
    private String description;

    @OneToMany
    @JoinColumn(name = "publisher_id") // we need to duplicate the physical information
    @JsonIgnore
    private Set<Product> productSet;

    public Publisher(Long id)
    {
     this.id=id;
    }

    public Publisher(String name, String address,
                     Integer phoneNumber,
                     String email,
                     Date established_year,
                     String description)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.established_year = established_year;
        this.description = description;
    }
}
