package com.outbidme.model.product;

public class Product {
    private double id;
    private String name;
    private String description;
    private double price;

    public Product(double id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getId() {
        return id;
    }
}
