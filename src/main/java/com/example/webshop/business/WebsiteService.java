package com.example.webshop.business;

import com.example.webshop.data.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class WebsiteService {
    @Autowired
    PersonRepository personRepository;
    Person person;

    WebsiteService() {

    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person login(String loginUser, String password) {
        List<Person> personList = personRepository.findByEmailAndPassWord(loginUser, password);
        person = personList.get(1);
        return person;
    }

    public String checkIfUserExist(String loginUser, String password) {
        List<Person> personList = personRepository.findByEmailAndPassWord(loginUser, password);
        if (personList.isEmpty()) {
            person = personRepository.save(new Person(loginUser, password));
            return "";
        }
        return "This user already exist";


    }
}
