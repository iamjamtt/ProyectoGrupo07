package com.example.proyectogrupo07;

public class Product {
    public int productId;
    public String name;
    public String description;
    public String price;
    public String image;

    public Product(int productId, String name, String description, String price, String image) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getProductId() { return productId; }

    public void setProductId(int productId) { this.productId = productId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
