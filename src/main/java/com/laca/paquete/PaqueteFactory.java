package com.laca.paquete;

public class PaqueteFactory {
    public static Paquete crearPaquete() {
        return new Paquete();
    }

    public static Paquete crearPaquete(int packageID, String name, String description, double weight, double price, double sizeHeight, double sizeWidth, int clientID, int routeID, String status) {
        Paquete paquete = new Paquete();

        paquete.setPackageID(packageID);
        paquete.setName(name);
        paquete.setDescription(description);
        paquete.setWeight(weight);
        paquete.setPrice(price);
        paquete.setSizeHeight(sizeHeight);
        paquete.setSizeWidth(sizeWidth);
        paquete.setClientID(clientID);
        paquete.setRouteID(routeID);
        paquete.setStatus(status);

        return paquete;
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