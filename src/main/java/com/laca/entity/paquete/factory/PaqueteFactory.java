package com.laca.entity.paquete.factory;

import com.laca.entity.paquete.objects.Paquete;

public class PaqueteFactory {
    public static Paquete creacrPaquete() {
        return new Paquete();
    }

    public static Paquete creacrPaquete(String tipo, double peso, String nombre, String Descripcion, double precio, double altura, double ancho) {
        return new Paquete(tipo,peso,nombre,Descripcion,precio,altura,ancho);
    }
}