package com.onlinebook.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category  extends BaseEntity {


    @Column(name = "name")
    private String name;

//    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> product=new HashSet<>();

    @Column(name = "description")
    private String description;
}
