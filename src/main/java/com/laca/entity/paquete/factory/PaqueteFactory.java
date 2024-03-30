package com.laca.entity.paquete.factory;

import com.laca.entity.paquete.objects.Paquete;

public class PaqueteFactory {
    public static Paquete creacrPaquete() {
        return new Paquete();
    }

    public static Paquete creacrPaquete(String type, double weight, String name, String description, double price, double sizeHeight, double sizeWidth) {
        return new Paquete(type,weight,name,description,price,sizeHeight,sizeWidth);
    }
}