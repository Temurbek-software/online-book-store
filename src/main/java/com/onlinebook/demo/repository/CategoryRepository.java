package com.onlinebook.demo.repository;

import com.onlinebook.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Product,Long>
{

}
