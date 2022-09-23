package com.onlinebook.demo.repository;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.AuthorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{
    @Query(value = "select (select count(*) from (select * from author s " +
            "where s.first_name =:firstname limit 1) " +
            "as author)>0",nativeQuery = true)
    boolean existsByFirstName(@Param("firstname") String firstname);

    @Query(value = "select (select count(*) from (select * from author s " +
            "where s.last_name =:lastName limit 1) " +
            "as author)>0",nativeQuery = true)
    boolean existsByLastName(@Param("lastName") String lastName);

    @Query(value = "select (select count(*) from (select * from author s " +
            "where s.email =:email limit 1) " +
            "as author)>0",nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

    @Query(value = "select (select count(*) from (select * from author s " +
            "where s.phone_number =:phoneNumber limit 1) " +
            "as author)>0",nativeQuery = true)
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
