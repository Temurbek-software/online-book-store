package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publisher")
public class Publisher extends BaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "adress")
    private String address;

    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "estabilishedYear")
    private Date established_year;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "publisher_id") // we need to duplicate the physical information
    @JsonIgnore
    private Set<Product> productSet;

    public Publisher(String name, String address,
                     Integer phoneNumber,
                     String email,
                     Date established_year,
                     String description) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.established_year = established_year;
        this.description = description;
    }
}
