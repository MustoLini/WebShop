package com.example.webshop.data;

import com.example.webshop.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    List<Person> findByEmailAndPassWord(String email, String password);

}
