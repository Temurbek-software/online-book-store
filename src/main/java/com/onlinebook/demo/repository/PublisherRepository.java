package com.onlinebook.demo.repository;

import com.onlinebook.demo.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long>
{

}
