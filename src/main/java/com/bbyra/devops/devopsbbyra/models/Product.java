package com.bbyra.devops.devopsbbyra.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String details;
    private Float price;
    private Integer quantity;
    private LocalDate added_date;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", added_date=" + added_date +
                '}';
    }

    public Product(Long id, String name, String details, Float price, Integer quantity, LocalDate added_date) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
        this.added_date = added_date;
    }

    public Product(String name, String details, Float price, Integer quantity, LocalDate added_date) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
        this.added_date = added_date;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getAdded_date() {
        return added_date;
    }

    public void setAdded_date(LocalDate added_date) {
        this.added_date = added_date;
    }


}
