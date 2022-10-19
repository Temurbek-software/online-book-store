package com.onlinebook.demo.repository;

import com.onlinebook.demo.entity.Category;
import com.onlinebook.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>
{
    @Query(value = "select * from category s where s.is_deleted=:deleted",nativeQuery = true)
    List<Category> getAllByCategoryIfNotDeleted(boolean deleted);
}
