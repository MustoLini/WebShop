package com.example.webshop.business;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;
    int amount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public CartItem() {
    }

    public CartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void removeOneFromAmount() {
        this.amount--;
    }

    @Override
    public String toString() {
        return
                "Product: " + product +
                ", Amount: " + amount ;
    }
}
