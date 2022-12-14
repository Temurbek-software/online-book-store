package com.onlinebook.demo.repository;

import com.onlinebook.demo.entity.Category;
import com.onlinebook.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>
{

}
