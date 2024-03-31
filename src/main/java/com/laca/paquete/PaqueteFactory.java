package com.laca.paquete;

public class PaqueteFactory {
    public static Paquete creacrPaquete() {
        return new Paquete();
    }

    public static Paquete creacrPaquete(String type, double weight, String name, String description, double price, double height, double width) {
        return new Paquete(type,weight,name,description,price,height,width);
    }
}