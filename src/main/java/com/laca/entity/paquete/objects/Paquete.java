package com.laca.entity.paquete.objects;

public class Paquete {
    String tipo;
    double peso;
    String nombre;
    String Descripcion;
    double precio;
    double altura;
    double ancho;

    public Paquete() {}

    public Paquete(String tipo, double peso, String nombre, String Descripcion, double precio, double altura, double ancho) {
        this.tipo = tipo;
        this.peso = peso;
        this.nombre = nombre;
        this.Descripcion = Descripcion;
        this.precio = precio;
        this.altura = altura;
        this.ancho = ancho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
}
