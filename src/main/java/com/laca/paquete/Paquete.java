package com.laca.paquete;

public class Paquete {
    private int packageID;
    private String type;
    private String name;
    private String description;
    private double weight;
    private double price;
    private double sizeHeight;
    private double sizeWidth;
    private int clientID;
    private int routeID;
    private String status;

    public Paquete() {}

    public Paquete(int packageID, String name, String description, double weight, double price, double sizeHeight, double sizeWidth, int clientID, int routeID, String status) {
        this.packageID = packageID;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
        this.clientID = clientID;
        this.routeID = routeID;
        this.status = status;
    }



    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}