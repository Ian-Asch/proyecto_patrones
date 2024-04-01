package com.laca.paquete;

public class PaqueteFactory {
    public static Paquete crearPaquete() {
        return new Paquete();
    }

    public static Paquete crearPaquete(int packageID, String name, String description, double weight, double price, double sizeHeight, double sizeWidth, int clientID, int routeID, String status) {
        return new Paquete(packageID,name,description,weight,price,sizeHeight,sizeWidth,clientID,routeID,status);
    }

    public static Paquete copyPaquete(Paquete paquete) {
        Paquete copiaPaquete = new Paquete();

        copiaPaquete.setPackageID(paquete.getPackageID());
        copiaPaquete.setType(paquete.getType());
        copiaPaquete.setName(paquete.getName());
        copiaPaquete.setDescription(paquete.getDescription());
        copiaPaquete.setWeight(paquete.getWeight());
        copiaPaquete.setPrice(paquete.getPrice());
        copiaPaquete.setSizeHeight(paquete.getSizeHeight());
        copiaPaquete.setSizeWidth(paquete.getSizeWidth());
        copiaPaquete.setClientID(paquete.getClientID());
        copiaPaquete.setRouteID(paquete.getRouteID());
        copiaPaquete.setStatus(paquete.getStatus());

        return copiaPaquete;
    }
}