package com.example.webshop.business;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    //Order Should be
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrders;

    private String passWord;

    public Person() {

    }

    public Person(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
        customerOrders= new ArrayList<>();

    }

    public Person(Long id, String name, List<CustomerOrder> customerOrders, String passWord) {
        this.id = id;
        this.name = name;
        this.customerOrders = customerOrders;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
