package com.example.webshop.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Email
    private String email;
    //Order Should be
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrders;
    @NotBlank
    private String passWord;

    public Person() {

    }

    public Person(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
        customerOrders= new ArrayList<>();

    }

    public Person(Long id, String email, List<CustomerOrder> customerOrders, String passWord) {
        this.id = id;
        this.email = email;
        this.customerOrders = customerOrders;
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
