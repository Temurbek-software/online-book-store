package com.onlinebook.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebook.demo.entity.template.BaseEntity;
import com.onlinebook.demo.payload.CompanyDTO;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company extends BaseEntity
{
   @Column(name ="nameOfCompany")
   @NotNull
   private String nameOfCompany;

   @Column(name = "address")
   @NotNull
   private String address;

   @Column(name = "phoneNumber")
   @NotNull
   private String phoneNumber;

   @Column(name = "email")
   @NotNull
   private String email;

   @Column(name = "yearOfPublished")
   @NotNull
   private Date yearOfPublished;

   @Column(name = "description")
   @NotNull
   private String description;

   @OneToMany(mappedBy = "productCompany",
           fetch = FetchType.LAZY,
           orphanRemoval = true)
   @JsonIgnore
   private Set<Product> companyProduct=new HashSet<>();


}
