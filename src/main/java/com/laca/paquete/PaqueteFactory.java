package com.laca.paquete;

public class PaqueteFactory {
    public static Paquete creacrPaquete() {
        return new Paquete();
    }

    public static Paquete crearPaquete(int packageID, String name, String description, double weight, double price, double sizeHeight, double sizeWidth, int clientID, int routeID, String status) {
        return new Paquete(packageID,name,description,weight,price,sizeHeight,sizeWidth,clientID,routeID,status);
    }
}