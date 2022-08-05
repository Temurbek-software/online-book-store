package com.onlinebook.demo.entity;

import com.onlinebook.demo.entity.template.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category  extends BaseEntity
{
    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> product=new HashSet<>();

    @Column(name = "description")
    @NotNull
    private String description;

}
