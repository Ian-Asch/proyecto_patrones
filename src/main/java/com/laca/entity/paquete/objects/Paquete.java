package com.laca.entity.paquete.objects;

public class Paquete {
    String type;
    double weight;
    String name;
    String description;
    double price;
    double sizeHeight;
    double sizeWidth;

    public Paquete() {}

    public Paquete(String type, double weight, String name, String description, double price, double sizeHeight, double sizeWidth) {
        this.type = type;
        this.weight = weight;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(double sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public double getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(double sizeWidth) {
        this.sizeWidth = sizeWidth;
    }
}