package com.onlinebook.demo.repository;

import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.payload.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query(value = "select * from product s where s.is_deleted=:false",nativeQuery = true)
    List<ProductDTO> getAllByProductsIfNotDeleted();

    @Query(value = "select * from product s where s.is_deleted=:true", nativeQuery = true)
    List<ProductDTO> getAllProductsIfDeleted();

//    @Transactional
//    @Modifying
//    @Query(value = "",nativeQuery = true)
//    void insertNewProduct(@Param("newProduct") ProductDTO productDTO);

}
