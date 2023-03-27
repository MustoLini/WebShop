package com.example.webshop.data;

import com.example.webshop.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Person,Long> {
    Optional<Person>findByName(String name);

}
