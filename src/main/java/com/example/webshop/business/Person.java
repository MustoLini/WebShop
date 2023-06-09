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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrders;
    @NotBlank
    private String passWord;
    private boolean admin;

    public Person() {

    }

    public Person(String email, String passWord, Boolean admin) {
        this.email = email;
        this.passWord = passWord;
        customerOrders= new ArrayList<>();
        this.admin= admin;
    }
    public Person(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
        customerOrders= new ArrayList<>();
        this.admin= false;
    }


    public boolean isAdmin() {
        return admin;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Person(Long id, String email, List<CustomerOrder> customerOrders, String passWord, Boolean admin) {
        this.id = id;
        this.email = email;
        this.customerOrders = customerOrders;
        this.admin= admin;
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
    public void addOrder(CustomerOrder customerOrder){
        customerOrders.add(customerOrder);

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person" +
                " CustomerOrders: " + customerOrders
                ;
    }
}
